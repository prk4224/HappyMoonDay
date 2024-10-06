package com.korea.search.domain

import com.korea.search.domain.model.Artwork
import javax.inject.Inject

class FetchSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke() : Result<List<Artwork>> {
        return searchRepository.fetch()
    }
}
