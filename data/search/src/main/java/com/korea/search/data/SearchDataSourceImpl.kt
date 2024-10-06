package com.korea.search.data

import com.korea.network.api.MuseumSearchApi
import com.korea.network.model.SearchDTO
import retrofit2.Response
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val museumSearchApi: MuseumSearchApi,
) : SearchDataSource {

    override fun fetch(): Response<SearchDTO> {
        return museumSearchApi.search()
    }
}
