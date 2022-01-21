package es.usj.mastertsa.jchueca.cities.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import es.usj.mastertsa.jchueca.cities.databinding.FragmentCityDetailBinding
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.CityDetailState
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.CityDetailViewModel
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.CityDetailViewModelFactory
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.CityState
import kotlinx.coroutines.flow.collect

class CityDetailFragment(private val cityId: Int) : Fragment() {
    
    var _binding: FragmentCityDetailBinding? = null
    val binding: FragmentCityDetailBinding get() = _binding!!
    
    val cityDetailViewModel: CityDetailViewModel by viewModels {
        CityDetailViewModelFactory(requireContext())
    }
    
    val sightsAdapter = SightsAdapter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        cityDetailViewModel.getCityAndSights(cityId)
    
    
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            cityDetailViewModel.cityDetailStateFlow.collect { cityDetailState: CityDetailState ->
                setState(cityDetailState)
            }
        }
        
    }
    
    private fun setState(cityDetailState: CityDetailState){
    
        when(cityDetailState){
            CityDetailState.Loading -> binding.progressBar.visibility = View.VISIBLE
            is CityDetailState.Success -> {
                binding.progressBar.visibility = View.GONE
                val cityAndSights = cityDetailState.data
                binding.cityName.text = cityAndSights.city.name
                sightsAdapter.submitList(cityAndSights.sights)
            }
            is CityDetailState.Failure -> {
                Toast.makeText(context, "Failures!!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }
    
    companion object {
        @JvmStatic
        fun newInstance(cityId: Int) = CityDetailFragment(cityId)
    }
}