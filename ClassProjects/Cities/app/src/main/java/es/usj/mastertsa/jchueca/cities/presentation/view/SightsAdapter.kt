package es.usj.mastertsa.jchueca.cities.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.usj.mastertsa.jchueca.cities.databinding.SightItemBinding
import es.usj.mastertsa.jchueca.cities.domain.model.Sight

class SightsAdapter (): ListAdapter<Sight, SightsAdapter.SightViewHolder>(SightsDiffUtilCallback) {
    
    inner class SightViewHolder(val binding: SightItemBinding): RecyclerView.ViewHolder(binding.root)
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SightViewHolder {
        val binding = SightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SightViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: SightViewHolder, position: Int) {
        val sight = getItem(position)
        holder.binding.sightName.text = sight.sight_name
        holder.binding.sightDescription.text = sight.sight_description
        
    }
}

object SightsDiffUtilCallback: DiffUtil.ItemCallback<Sight>(){
    override fun areItemsTheSame(oldItem: Sight, newItem: Sight): Boolean {
        return oldItem.sight_id == newItem.sight_id
    }
    
    override fun areContentsTheSame(oldItem: Sight, newItem: Sight): Boolean {
        return oldItem.sight_id == newItem.sight_id
    }
    
}
