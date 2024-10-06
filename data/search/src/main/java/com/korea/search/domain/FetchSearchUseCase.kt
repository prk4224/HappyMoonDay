package com.korea.search.domain

import com.korea.search.domain.model.SearchEntity
import com.korea.search.domain.model.SearchParams
import javax.inject.Inject

class FetchSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke(params: SearchParams): Result<SearchEntity> {
        return searchRepository.fetch(params)
    }
}
