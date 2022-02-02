package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.mastertsa.jveron.ticketsdb.R
import es.usj.mastertsa.jveron.ticketsdb.databinding.FragmentHomeBinding
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.EventState
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.HomeViewModel
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment(), OnClickEventListener {
    var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding get() = _binding!!

    val user: User? = null

    val homeViewModel : HomeViewModel by viewModels {
        HomeViewModelFactory(requireContext())
    }

    val eventsAdapter = EventsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userButton.setOnClickListener {
            if (user != null) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, UserAndEventsFragment.newInstance(userId = user.id))
                    .addToBackStack(null)
                    .commit()
            }
        }

        childFragmentManager.setFragmentResultListener(BUY_TICKET_REQUEST_KEY, viewLifecycleOwner) { _, bundle ->
            val city : Event? = bundle.getParcelable(EVENT_KEY) as? Event
            if (user != null && city != null) {
                homeViewModel.addUserAndEvent(user, city)
            }
        }

        binding.eventsRecyclerView.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.eventsStateFlow.collect { eventState: EventState ->
                setState(eventState)
            }
        }

        homeViewModel.getData()
    }

    private fun setState(eventState: EventState) {
        when (eventState) {
            EventState.Loading -> binding.progressBar.visibility = View.VISIBLE
            is EventState.Success -> {
                binding.progressBar.visibility = View.GONE
                val eventsList = eventState.data
                eventsAdapter.submitList(eventsList)
            }
            is EventState.Failure -> {
                Toast.makeText(context, "Failure!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onEventClicked(event: Event) {
        user?.let { BuyTicketFragment(user = it, event = event) }
            ?.show(childFragmentManager, BUY_TICKET_TAG)
    }

}