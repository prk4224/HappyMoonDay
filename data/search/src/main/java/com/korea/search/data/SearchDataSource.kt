package com.korea.search.data

import com.korea.network.model.SearchDTO
import com.korea.search.domain.model.SearchParams
import retrofit2.Response

internal interface SearchDataSource {
    suspend fun fetch(params: SearchParams): Response<SearchDTO>
}
