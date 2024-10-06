package com.korea.network.api

import com.korea.network.model.SearchDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MuseumSearchApi {

    @GET("json/SemaPsgudInfoKorInfo/{path}")
    suspend fun search(
        @Path("path") path: String,
    ): Response<SearchDTO>
}
