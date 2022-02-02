package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import androidx.fragment.app.Fragment

class UserAndEventsFragment(val userId: Int) : Fragment() {

    companion object {
        fun newInstance(userId: Int) = UserAndEventsFragment(userId)
    }
}