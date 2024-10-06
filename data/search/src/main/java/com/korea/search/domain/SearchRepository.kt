package com.korea.search.domain

import com.korea.search.domain.model.Artwork

interface SearchRepository {
    suspend fun fetch(): Result<List<Artwork>>
}
