package com.korea.search.domain

import com.korea.network.model.SearchDTO
import retrofit2.Response
import javax.inject.Inject

class FetchSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke() : Response<SearchDTO> {
        return searchRepository.fetch()
    }
}
