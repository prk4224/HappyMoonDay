package com.korea.search.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.korea.search.databinding.ArtworkItemBinding
import com.korea.search.domain.model.Artwork

class SearchViewHolder(
    private val binding: ArtworkItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(artwork: Artwork) {
        binding.titleTv.text = artwork.title
        binding.writerManufactureTv.text = "${artwork.writer} (${artwork.manufactureYear})"
        binding.classNameTv.text = artwork.productClassName
        loadMainImage(artwork.imageUrl)
    }

    private fun loadMainImage(imageUrl: String) {
        Glide.with(binding.mainImageIv.context)
            .load(imageUrl)
            .centerCrop()
            .into(binding.mainImageIv)
    }
}
