package com.korea.boorkmark.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.korea.bookmark.model.BookmarkArtwork
import com.korea.boorkmark.databinding.ArtworkItemBinding

internal class BookmarkViewHolder(
    private val binding: ArtworkItemBinding,
    private val onClick: (BookmarkArtwork) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bookmarkArtwork: BookmarkArtwork) {
        binding.titleTv.text = bookmarkArtwork.title
        binding.writerManufactureTv.text =
            "${bookmarkArtwork.writer} (${bookmarkArtwork.manufactureYear})"
        binding.classNameTv.text = bookmarkArtwork.productClassName
        binding.root.setOnClickListener {
            onClick(bookmarkArtwork)
        }
        loadMainImage(bookmarkArtwork.imageUrl)
    }

    private fun loadMainImage(imageUrl: String) {
        Glide.with(binding.mainImageIv.context)
            .load(imageUrl)
            .centerCrop()
            .into(binding.mainImageIv)
    }
}
