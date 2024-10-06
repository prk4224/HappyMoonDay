package com.korea.search.domain.model

data class SearchParams(
    val startIndex: Int,
    val endIndex: Int,
    val productClassName: String? = null,
    val manufactureYear: String? = null,
    val productNameKorean: String? = null,
    val productNameEnglish: String? = null
)
