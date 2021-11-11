package cz.quanti.spacexrockets_janpejsar.rocketslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.ThemeUtils
import cz.quanti.spacexrockets_janpejsar.databinding.ItemRocketBinding

class RocketsAdapter: ListAdapter<RocketItem, RocketsAdapter.RocketViewHolder>(DIFF_CALLBACK) {
    var onItemClickListener: ((item: RocketItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_rocket, parent, false))
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.binding.rocket = getItem(position)

        if (position == itemCount - 1) {
            holder.binding.cardView.setBackgroundResource(R.drawable.item_rocket_last)
            holder.binding.divider.visibility = View.GONE
        } else {
            holder.binding.cardView.setBackgroundColor(
                ThemeUtils.getAttributeColor(holder.binding.root.context, R.attr.colorSurface)
            )
            holder.binding.divider.visibility = View.VISIBLE
        }
    }

    override fun submitList(list: List<RocketItem>?) {
        submitList(list, null)
    }

    override fun submitList(list: List<RocketItem>?, commitCallback: Runnable?) {
        val oldLast = itemCount
        super.submitList(list, commitCallback)
        notifyItemChanged(oldLast)
    }

    inner class RocketViewHolder(val binding: ItemRocketBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.mainLayout.setOnClickListener {
                onItemClickListener?.invoke(getItem(adapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RocketItem>() {
            override fun areItemsTheSame(oldItem: RocketItem, newItem: RocketItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RocketItem, newItem: RocketItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}