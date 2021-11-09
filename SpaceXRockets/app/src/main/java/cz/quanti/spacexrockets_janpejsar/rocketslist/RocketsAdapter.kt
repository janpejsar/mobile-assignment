package cz.quanti.spacexrockets_janpejsar.rocketslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.ItemRocketBinding
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket

class RocketsAdapter: ListAdapter<Rocket, RocketsAdapter.RocketViewHolder>(DIFF_CALLBACK) {
    var onItemClickListener: ((item: Rocket) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_rocket, parent, false))
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.binding.rocket = getItem(position)
    }

    inner class RocketViewHolder(val binding: ItemRocketBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(getItem(adapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Rocket>() {
            override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
                return oldItem == newItem
            }

        }
    }
}