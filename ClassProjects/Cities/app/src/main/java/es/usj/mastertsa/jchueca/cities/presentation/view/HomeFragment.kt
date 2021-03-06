package es.usj.mastertsa.jchueca.cities.presentation.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.mastertsa.jchueca.cities.R
import es.usj.mastertsa.jchueca.cities.databinding.FragmentHomeBinding
import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.CityState
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.HomeViewModel
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.flow.collect


class HomeFragment : Fragment() {

    var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding get() = _binding!!

    val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(requireContext())
    }

    val citiesAdapter = CitiesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.citiesToolbar.inflateMenu(R.menu.cities_menu)
        binding.citiesToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_all_cities -> {
                    homeViewModel.setFilter(CityFilter.ALL_CITIES)
                    true
                }
                R.id.action_sunny_cities -> {
                    homeViewModel.setFilter(CityFilter.SUNNY_CITIES)
                    true
                }
                R.id.action_cloudy_cities -> {
                    homeViewModel.setFilter(CityFilter.CLOUDY_CITIES)
                    true
                }
                else -> false
            }
            
        }
        
        childFragmentManager.setFragmentResultListener(ADD_CITY_REQUEST_KEY, viewLifecycleOwner) { _, bundle ->
            val city : City? = bundle.getParcelable(CITY_KEY) as? City
            if (city != null) {
                homeViewModel.addCity(city)
            }
        }
        
        binding.addCityButton.setOnClickListener {
            val addCityFragment = AddCityFragment()
            addCityFragment.show(childFragmentManager, ADD_CITY_TAG)
        }
        
        binding.citiesRecyclerView.apply {
            adapter = citiesAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.citiesStateFlow.collect { cityState: CityState ->
                setState(cityState)
            }
        }
        
        homeViewModel.getData()
        
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            homeViewModel.filterCityState.collect { filter ->
                setFilter(filter)
            }
        }
        
        homeViewModel.getFilter()
    }

    private fun setFilter(filter: CityFilter){
    
        val allCities = binding.citiesToolbar.menu.findItem(R.id.action_all_cities)
        val sunshineCities = binding.citiesToolbar.menu.findItem(R.id.action_sunny_cities)
        val cloudyCities = binding.citiesToolbar.menu.findItem(R.id.action_cloudy_cities)
        
        reset(allCities, sunshineCities, cloudyCities)
        
        when (filter){
            CityFilter.ALL_CITIES -> setSelectedMenuItem(allCities)
            CityFilter.SUNNY_CITIES -> setSelectedMenuItem(sunshineCities)
            CityFilter.CLOUDY_CITIES -> setSelectedMenuItem(cloudyCities)
        }
    }
    
    private fun setSelectedMenuItem(menuItem: MenuItem){
        menuItem.icon.setTint(Color.YELLOW)
    }
    
    private fun reset(vararg menuItems: MenuItem){
        menuItems.forEach { menuItem ->
            menuItem.icon.setTint(Color.WHITE)
        }
    }
    
    private fun setState (cityState: CityState)  {
        when(cityState){
            CityState.Loading -> binding.progressBar.visibility = View.VISIBLE
            is CityState.Success -> {

                binding.progressBar.visibility = View.GONE
                val citiesList = cityState.data
                citiesAdapter.submitList(citiesList)

            }
            is CityState.Failure -> {
                Toast.makeText(context, "Failures!!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
