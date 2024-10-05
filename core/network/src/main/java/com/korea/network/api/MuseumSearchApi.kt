package com.korea.network.api

import retrofit2.http.GET
import retrofit2.http.Path

interface MuseumSearchApi {

    @GET("/json/SemaPsgudInfoKorInfo/")
    fun search(
        @Path("START_INDEX") startIndex: Int,
        @Path("END_INDEX") endIndex: Int,
        @Path("prdct_cl_nm") product1ClassName : Int,
        @Path("manage_no_year") manageNoYear: Int,
        @Path("prodct_nm_korean") productNameKorean: Int,
        @Path("prodct_nm_eng") productNameEnglish : Int,
    )
}
