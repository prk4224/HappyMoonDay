package com.korea.network.api

import com.korea.network.model.SearchDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MuseumSearchApi {

    @GET("/json/SemaPsgudInfoKorInfo")
    fun search(
        @Query("startIndex") startIndex: Int = 0,
        @Query("endIndex") endIndex: Int = 200,
        @Query("prdct_cl_nm") productClassName: String? = "조각",
        @Query("manage_no_year") manufactureYear: String? = "1988",
        @Query("prodct_nm_korean") productNameKorean: String? = "꿈",
        @Query("prodct_nm_eng") productNameEnglish: String? = null,
    ): Response<SearchDTO>
}
