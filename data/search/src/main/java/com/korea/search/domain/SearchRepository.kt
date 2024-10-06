package com.korea.search.domain

import com.korea.network.model.SearchDTO
import retrofit2.Response

interface SearchRepository {
    fun fetch(): Response<SearchDTO>
}
