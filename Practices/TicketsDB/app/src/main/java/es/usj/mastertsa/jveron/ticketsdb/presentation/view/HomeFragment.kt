package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.mastertsa.jveron.ticketsdb.R
import es.usj.mastertsa.jveron.ticketsdb.data.NO_USER
import es.usj.mastertsa.jveron.ticketsdb.data.SaveLoadPreferences
import es.usj.mastertsa.jveron.ticketsdb.data.repository.UserMapper
import es.usj.mastertsa.jveron.ticketsdb.databinding.FragmentHomeBinding
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserAndEvent
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.EventState
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.HomeViewModel
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.HomeViewModelFactory
import es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel.UserState
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment(), OnClickEventListener {
    var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding get() = _binding!!

    var user: User? = null
    var checkingUser: User? = null
    var checkingSignUpUser: User? = null

    val homeViewModel : HomeViewModel by viewModels {
        HomeViewModelFactory(requireContext())
    }

    val eventsAdapter = EventsAdapter(this)

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

        binding.logoutButton.hide()

        // Buttons
        binding.logoutButton.setOnClickListener {
            if (user != null) {
                logOut()
                checkLoggedIn()
            }
        }

        binding.userButton.setOnClickListener {
            checkLoggedIn()

            if (user != null) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, ProfileFragment.newInstance(user = user!!))
                    .addToBackStack(null)
                    .commit()
            }
        }

        // Get events and selling tickets
        childFragmentManager.setFragmentResultListener(BUY_TICKET_REQUEST_KEY, viewLifecycleOwner) { _, bundle ->
            val userAndEvent : UserAndEvent? = bundle.getParcelable(BUY_TICKET_KEY) as? UserAndEvent
            if (userAndEvent != null) {
                homeViewModel.addUserAndEvent(userAndEvent.user, userAndEvent.event)
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

        // Login
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.usersStateFlow.collect { userState: UserState ->
                checkLoginFromState(userState)
            }
        }

        childFragmentManager.setFragmentResultListener(LOG_IN_REQUEST_KEY, viewLifecycleOwner) { _, bundle ->
            val user : User? = bundle.getParcelable(LOG_IN_KEY) as? User
            if (user != null) {
                this.checkingUser = User(user.email, UserMapper.saltAndHash(user.password), user.name)
                homeViewModel.getUser(user.email)
            }
        }

        checkLoggedIn()

        // Signup
        childFragmentManager.setFragmentResultListener(SIGN_UP_REQUEST_KEY, viewLifecycleOwner) { _, bundle ->
            val user : User? = bundle.getParcelable(SIGN_UP_KEY) as? User
            if (user != null) {
                this.checkingSignUpUser = User(user.email, UserMapper.saltAndHash(user.password), user.name)
                homeViewModel.getUser(user.email)
            }
        }
    }

    private fun checkLoggedIn() {
        if (user == null) {
            val possibleUser: User? = SaveLoadPreferences.load(activity as AppCompatActivity)
            if (possibleUser != null) {
                this.checkingUser = possibleUser
                homeViewModel.getUser(possibleUser.email)
            }
            else {
                val logInFragment = LogInFragment()
                logInFragment.show(childFragmentManager, LOG_IN_TAG)
            }
        }
    }

    private fun logOut() {
        this.user = null
        this.checkingUser = null
        binding.logoutButton.hide()

        SaveLoadPreferences.save(activity as AppCompatActivity, NO_USER)
    }

    private fun checkLoginFromState(userState: UserState) {
        when (userState) {
            UserState.Loading -> binding.progressBar.visibility = View.VISIBLE
            is UserState.Success -> {
                if (this.checkingSignUpUser == null) {
                    checkLogin(userState.data)
                }
                else {
                    if (userState.data == NO_USER) {
                        // SIGN UP
                        Toast.makeText(context, "Signed Up and Logged In", Toast.LENGTH_SHORT).show()
                        homeViewModel.addUser(this.checkingSignUpUser!!)
                        this.user = this.checkingSignUpUser
                        SaveLoadPreferences.save(activity as AppCompatActivity, this.user!!)
                        this.checkingUser = this.user
                        this.checkingSignUpUser = null
                        binding.logoutButton.show()
                    }
                    else {
                        Toast.makeText(context, "This user already exists!", Toast.LENGTH_SHORT).show()

                        checkLoggedIn()
                    }
                }
            }
            is UserState.Failure -> {
                Toast.makeText(context, "Wrong email or password!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE

                checkLoggedIn()
            }
        }
    }

    private fun checkLogin(user: User) {
        binding.progressBar.visibility = View.GONE
        if (checkingUser?.password == user.password) {
            Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show()
            this.user = user
            SaveLoadPreferences.save(activity as AppCompatActivity, user)
            binding.logoutButton.show()
        }
        else {
            Toast.makeText(context, "Wrong email or password!", Toast.LENGTH_SHORT).show()

            checkLoggedIn()
        }
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
        if (user == null){
            checkLoggedIn()
        }
        else {
            user?.let { BuyTicketFragment(user = it, event = event) }
                ?.show(childFragmentManager, BUY_TICKET_TAG)
        }
    }

}