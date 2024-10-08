package com.korea.search.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.korea.search.databinding.ArtworkItemBinding
import com.korea.search.domain.model.Artwork

internal class SearchViewHolder(
    private val binding: ArtworkItemBinding,
    private val onClick: (Artwork) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(artwork: Artwork) {
        binding.titleTv.text = artwork.title
        binding.writerManufactureTv.text = "${artwork.writer} (${artwork.manufactureYear})"
        binding.classNameTv.text = artwork.productClassName
        binding.root.setOnClickListener {
            onClick(artwork)
        }
        loadMainImage(artwork.imageUrl)
    }

    private fun loadMainImage(imageUrl: String) {
        Glide.with(binding.mainImageIv.context)
            .load(imageUrl)
            .centerCrop()
            .into(binding.mainImageIv)
    }
}
