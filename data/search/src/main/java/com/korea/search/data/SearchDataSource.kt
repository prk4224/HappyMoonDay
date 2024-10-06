package com.korea.search.data

import com.korea.network.model.SearchDTO
import retrofit2.Response

interface SearchDataSource {
    suspend fun fetch(): Response<SearchDTO>
}
