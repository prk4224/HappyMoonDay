package com.korea.search.data

import com.korea.network.model.SearchDTO
import com.korea.search.common.ApiUtils.safeApiCall
import com.korea.search.domain.SearchRepository
import com.korea.search.domain.model.Artwork
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
                    artworks = artworks
                )
            }
        )
    }

    private fun SearchDTO.Row.convertToArtwork(): Artwork {
        return Artwork(
            imageUrl = this.mainImage ?: "",
            title = this.productNameKorean ?: "",
            writer = this.writerName ?: "",
            manufactureYear = this.manufactureYear ?: "",
            productClassName = this.productClassName ?: "",
        )
    }
}
