package com.korea.search.domain

import com.korea.search.domain.model.SearchEntity
import com.korea.search.domain.model.SearchParams

interface SearchRepository {
    suspend fun fetch(params: SearchParams): Result<SearchEntity>
}
