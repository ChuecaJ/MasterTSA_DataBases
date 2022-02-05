package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import es.usj.mastertsa.jveron.ticketsdb.databinding.FragmentBuyTicketBinding
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserAndEvent

const val BUY_TICKET_TAG = "BuyTicketTag"
const val BUY_TICKET_REQUEST_KEY = "BuyTicketRequestKey"
const val BUY_TICKET_KEY = "BuyTicketKey"

class BuyTicketFragment (val user: User, val event: Event): DialogFragment() {

    var _binding : FragmentBuyTicketBinding? = null
    val binding : FragmentBuyTicketBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEventName.text = event.name
        binding.tvEventDescription.text = event.description
        binding.tvEventPrice.text = event.price.toString().plus(" $")

        binding.buyButton.setOnClickListener {
            val event = getData()
            val bundle = Bundle()
            bundle.putParcelable(BUY_TICKET_KEY, event)
            setFragmentResult(BUY_TICKET_REQUEST_KEY, bundle)
            dismiss()
        }
    }

    private fun getData() : UserAndEvent {
       return UserAndEvent(user = user, event = event)
    }

}