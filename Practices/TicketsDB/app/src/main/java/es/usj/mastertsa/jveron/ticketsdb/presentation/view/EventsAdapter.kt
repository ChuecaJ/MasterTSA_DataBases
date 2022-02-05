package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.usj.mastertsa.jveron.ticketsdb.databinding.EventItemBinding
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event

class EventsAdapter (val onClickEventListener: OnClickEventListener) : ListAdapter<Event, EventsAdapter.EventViewHolder>(CitiesDiffUtilCallback) {

    inner class EventViewHolder(val binding: EventItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.binding.eventName.text = event.name
        holder.binding.root.setOnClickListener{ onClickEventListener.onEventClicked(event) }
    }
}

object CitiesDiffUtilCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }
}

interface OnClickEventListener{
    fun onEventClicked(event: Event)
}