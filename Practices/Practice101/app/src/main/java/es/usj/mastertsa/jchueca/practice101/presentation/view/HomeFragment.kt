package es.usj.mastertsa.jchueca.practice101.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import es.usj.mastertsa.jchueca.practice101.databinding.FragmentHomeBinding
import es.usj.mastertsa.jchueca.practice101.domain.model.PracticeData
import es.usj.mastertsa.jchueca.practice101.presentation.viewmodel.HomeState
import es.usj.mastertsa.jchueca.practice101.presentation.viewmodel.HomeViewModel
import es.usj.mastertsa.jchueca.practice101.presentation.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment() {
    
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(requireContext())
    }
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.practiceStateFlow.collect { homeState ->
                setState(homeState)
            }
        }
        viewModel.getData()
        setButtons()
    }
    
    private fun setState(homeState: HomeState) {
        when (homeState) {
            is HomeState.Failure -> {
                binding.loadingProgressBar.isVisible = false
                Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
            }
            HomeState.Loading -> binding.loadingProgressBar.isVisible = true
            is HomeState.Success -> {
                binding.loadingProgressBar.isVisible = false
                binding.homeTextView.text = homeState.practiceData.name
            }
        }
    }
    
    private fun setButtons() {
        binding.addDataButton.setOnClickListener {
            addData()
        }
        binding.deleteDataButton.setOnClickListener {
            deleteData()
        }
        binding.updateDataButton.setOnClickListener {
            updateData()
        }
    }
    
    private fun addData() {
        val name = binding.dataEditText.text.toString()
        val practiceData = PracticeData(name)
        viewModel.addData(practiceData)
    }
    
    private fun updateData() {
        val name = binding.dataEditText.text.toString()
        val practiceData = PracticeData(name)
        viewModel.updateData(practiceData)
    }
    
    private fun deleteData() {
        viewModel.deleteData()
    }
    
    companion object {
        fun newInstance() = HomeFragment()
    }
}