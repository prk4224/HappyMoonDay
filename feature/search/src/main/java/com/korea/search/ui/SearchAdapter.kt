package com.korea.search.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.korea.search.databinding.ArtworkItemBinding
import com.korea.search.domain.model.SearchArtwork

internal class SearchAdapter(
    private val onClick: (SearchArtwork) -> Unit,
) : ListAdapter<SearchArtwork, SearchViewHolder>(searchDiffUtil) {

    companion object {
        private val searchDiffUtil = object : DiffUtil.ItemCallback<SearchArtwork>() {
            override fun areItemsTheSame(
                oldItem: SearchArtwork,
                newItem: SearchArtwork,
            ): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(
                oldItem: SearchArtwork,
                newItem: SearchArtwork,
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
            ),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
