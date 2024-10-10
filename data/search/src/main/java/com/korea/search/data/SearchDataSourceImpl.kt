package com.korea.search.data

import com.korea.network.api.MuseumSearchApi
import com.korea.network.model.SearchDTO
import com.korea.search.domain.model.SearchParams
import retrofit2.Response
import javax.inject.Inject

internal class SearchDataSourceImpl @Inject constructor(
    private val museumSearchApi: MuseumSearchApi,
) : SearchDataSource {

    override suspend fun fetch(params: SearchParams): Response<SearchDTO> {
        return museumSearchApi.search(makePath(params))
    }

    private fun makePath(
        params: SearchParams,
    ): String {
        val className = params.productClassName?.let { "/$it" } ?: "/ "
        val year = params.manufactureYear?.let { "/$it" } ?: "/ "
        val nameKorean = params.productNameKorean?.let { "/$it" } ?: "/ "
        val nameEnglish = params.productNameEnglish?.let { "/$it" } ?: "/ "
        return "${params.startIndex}/${params.endIndex}${className}${year}${nameKorean}${nameEnglish}"
    }
}
