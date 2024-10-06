package com.korea.search.data

import com.korea.network.model.SearchDTO
import com.korea.search.common.ApiUtils.safeApiCall
import com.korea.search.domain.SearchRepository
import com.korea.search.domain.model.Artwork
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    override suspend fun fetch(): Result<List<Artwork>> {
        return safeApiCall(
            apiCall = {
                searchDataSource.fetch()
            },
            convert = { dto ->
                dto.semaPsgudInfoKorInfo?.rows?.map { row ->
                    row.convertToArtwork()
                } ?: listOf()
            }
        )
    }

    private fun SearchDTO.Row.convertToArtwork(): Artwork {
        return Artwork(
            imageUrl = this.mainImage ?: "",
            title = this.productNameKorean ?: "",
            writer = this.writerName ?: "",
            manuFactureYear = this.manuFactureYear ?: "",
            productClassName = this.productClassName ?: "",
        )
    }
}
