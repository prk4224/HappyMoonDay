package com.korea.search.data

import com.korea.common.utils.ApiUtils.safeApiCall
import com.korea.network.model.SearchDTO
import com.korea.search.domain.SearchRepository
import com.korea.search.domain.model.SearchArtwork
import com.korea.search.domain.model.SearchEntity
import com.korea.search.domain.model.SearchParams
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    override suspend fun fetch(params: SearchParams): Result<SearchEntity> {
        return safeApiCall(
            apiCall = {
                searchDataSource.fetch(params)
            },
            convert = { dto ->
                val artworks = dto.semaPsgudInfoKorInfo?.rows?.map { row ->
                    row.convertToArtwork()
                } ?: listOf()
                val totalCount = dto.semaPsgudInfoKorInfo?.listTotalCount ?: 0

                SearchEntity(
                    totalCount = totalCount,
                    searchArtworks = artworks
                )
            }
        )
    }

    private fun SearchDTO.Row.convertToArtwork(): SearchArtwork {
        return SearchArtwork(
            imageUrl = this.mainImage ?: "",
            title = this.productNameKorean ?: "",
            titleEnglish = this.productNameEnglish ?: "",
            writer = this.writerName ?: "",
            manufactureYear = this.manufactureYear ?: "",
            productClassName = this.productClassName ?: "",
            productStandard = this.productStandard ?: "",
            manageNoYear = this.manageNoYear ?: "",
            materialTechnic = this.materialTechnic ?: "",
        )
    }
}
