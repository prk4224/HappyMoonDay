package com.korea.search.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artwork(
    val imageUrl: String = "",
    val title: String = "",
    val titleEnglish: String = "",
    val writer: String = "",
    val manufactureYear: String = "",
    val productClassName: String = "",
    val productStandard: String = "",
    val manageNoYear: String = "",
    val materialTechnic: String = "",
) : Parcelable
