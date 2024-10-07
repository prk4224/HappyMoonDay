package com.korea.search.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.korea.search.databinding.ArtworkItemBinding
import com.korea.search.domain.model.Artwork

internal class SearchAdapter : ListAdapter<Artwork, SearchViewHolder>(searchDiffUtil) {

    companion object {
        private val searchDiffUtil = object : DiffUtil.ItemCallback<Artwork>() {
            override fun areItemsTheSame(
                oldItem: Artwork,
                newItem: Artwork,
            ): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(
                oldItem: Artwork,
                newItem: Artwork,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ArtworkItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
