package com.korea.boorkmark.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.korea.bookmark.model.BookmarkArtwork
import com.korea.boorkmark.databinding.ArtworkItemBinding

internal class BookmarkAdapter(
    private val onClick: (BookmarkArtwork) -> Unit,
) : ListAdapter<BookmarkArtwork, BookmarkViewHolder>(searchDiffUtil) {

    companion object {
        private val searchDiffUtil = object : DiffUtil.ItemCallback<BookmarkArtwork>() {
            override fun areItemsTheSame(
                oldItem: BookmarkArtwork,
                newItem: BookmarkArtwork,
            ): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(
                oldItem: BookmarkArtwork,
                newItem: BookmarkArtwork,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            ArtworkItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
