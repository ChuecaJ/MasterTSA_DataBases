package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import androidx.fragment.app.Fragment
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User

class UserAndEventsFragment(val user: User) : Fragment() {

    companion object {
        fun newInstance(user: User) = UserAndEventsFragment(user)
    }
}