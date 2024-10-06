package com.korea.search.domain.model

data class SearchEntity(
    val totalCount: Int,
    val artworks: List<Artwork>
)
