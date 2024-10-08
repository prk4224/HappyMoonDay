package com.korea.search.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.korea.search.databinding.ArtworkItemBinding
import com.korea.search.domain.model.SearchArtwork

internal class SearchViewHolder(
    private val binding: ArtworkItemBinding,
    private val onClick: (SearchArtwork) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(searchArtwork: SearchArtwork) {
        binding.titleTv.text = searchArtwork.title
        binding.writerManufactureTv.text = "${searchArtwork.writer} (${searchArtwork.manufactureYear})"
        binding.classNameTv.text = searchArtwork.productClassName
        binding.root.setOnClickListener {
            onClick(searchArtwork)
        }
        loadMainImage(searchArtwork.imageUrl)
    }

    private fun loadMainImage(imageUrl: String) {
        Glide.with(binding.mainImageIv.context)
            .load(imageUrl)
            .centerCrop()
            .into(binding.mainImageIv)
    }
}
