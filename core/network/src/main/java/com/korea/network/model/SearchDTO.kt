package com.korea.network.model

import com.google.gson.annotations.SerializedName

data class SearchDTO(
    @SerializedName("SemaPsgudInfoKorInfo")
    val semaPsgudInfoKorInfo : SemaPsgudInfoKorInfo?,
) {
    data class SemaPsgudInfoKorInfo(
        @SerializedName("list_total_count")
        val listTotalCount: Int?,
        @SerializedName("RESULT")
        val result: Result?,
        @SerializedName("row")
        val rows: List<Row>?,
    )

    data class Result(
        @SerializedName("CODE")
        val code: String?,
        @SerializedName("MESSAGE")
        val message: String?,
    )

    data class Row(
        @SerializedName("prdct_cl_nm")
        val productClassName: String?,
        @SerializedName("manage_no_year")
        val manageNoYear: String?,
        @SerializedName("prdct_nm_korean")
        val productNameKorean: String?,
        @SerializedName("prdct_nm_eng")
        val productNameEnglish: String?,
        @SerializedName("prdct_stndrd")
        val productStandard: String?,
        @SerializedName("mnfct_year")
        val manufactureYear: String?,
        @SerializedName("matrl_technic")
        val materialTechnic: String?,
        @SerializedName("prdct_detail")
        val productDetail: String?,
        @SerializedName("writr_nm")
        val writerName: String?,
        @SerializedName("main_image")
        val mainImage: String?,
        @SerializedName("thumb_image")
        val thumbImage: String?,
    )
}
