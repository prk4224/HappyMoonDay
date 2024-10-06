package com.korea.search.data

import com.korea.network.model.SearchDTO
import com.korea.search.domain.SearchRepository
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
): SearchRepository {
    override fun fetch(): Response<SearchDTO> {
        return searchDataSource.fetch()
    }
}
