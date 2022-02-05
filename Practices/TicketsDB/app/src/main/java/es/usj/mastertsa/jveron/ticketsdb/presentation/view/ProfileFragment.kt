package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.mastertsa.jveron.ticketsdb.data.NO_USER
import es.usj.mastertsa.jveron.ticketsdb.data.SaveLoadPreferences
import es.usj.mastertsa.jveron.ticketsdb.databinding.FragmentProfileBinding
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.ProfileViewModel
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.ProfileViewModelFactory
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.UserAndEventsState
import kotlinx.coroutines.flow.collect

class ProfileFragment(val user: User) : Fragment(), OnClickEventListener {

    var _binding: FragmentProfileBinding? = null
    val binding: FragmentProfileBinding get() = _binding!!

    val profileViewModel : ProfileViewModel by viewModels {
        ProfileViewModelFactory(requireContext(), user = user)
    }

    val eventsAdapter = EventsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Buttons
        binding.homeButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Get events
        binding.eventsRecyclerView.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            profileViewModel.userAndEventsStateFlow.collect { userAndEventsState: UserAndEventsState ->
                setState(userAndEventsState)
            }
        }

        profileViewModel.getData()
    }

    private fun setState(userAndEventsState: UserAndEventsState) {
        when (userAndEventsState) {
            UserAndEventsState.Loading -> binding.progressBar.visibility = View.VISIBLE
            is UserAndEventsState.Success -> {
                binding.progressBar.visibility = View.GONE
                val eventsList = userAndEventsState.data.events
                binding.userName.text = userAndEventsState.data.user.name
                eventsAdapter.submitList(eventsList)
            }
            is UserAndEventsState.Failure -> {
                Toast.makeText(context, "Failure!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    companion object {
        fun newInstance(user: User) = ProfileFragment(user)
    }

    override fun onEventClicked(event: Event) {
        // user?.let { BuyTicketFragment(user = it, event = event) }
        //     ?.show(childFragmentManager, BUY_TICKET_TAG)
    }
}