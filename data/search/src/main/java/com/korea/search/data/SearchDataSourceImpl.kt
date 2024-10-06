package com.korea.search.data

import com.korea.network.api.MuseumSearchApi
import com.korea.network.model.SearchDTO
import com.korea.search.domain.model.SearchParams
import retrofit2.Response
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val museumSearchApi: MuseumSearchApi,
) : SearchDataSource {

    override suspend fun fetch(): Response<SearchDTO> {

        val path = makePath(
            SearchParams(
                startIndex = 0,
                endIndex = 10,
                productClassName = "조각",
                manufactureYear = "1988",
                productNameKorean = "꿈",
                productNameEnglish = "dream"
            )
        )

        return museumSearchApi.search(path)
    }

    private fun makePath(
        params: SearchParams
    ): String {
        val className = params.productClassName?.let { "/$it" } ?: ""
        val year = params.manufactureYear?.let { "/$it" } ?: ""
        val nameKorean = params.productNameKorean?.let { "/$it" } ?: ""
        val nameEnglish = params.productNameEnglish?.let { "/$it" } ?: ""
        return "${params.startIndex}/${params.endIndex}${className}${year}${nameKorean}${nameEnglish}"
    }
}
