package com.korea.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ARTWORK_LIST_TABLE")
data class ArtworkEntity(
    @PrimaryKey val imageUrl: String = "",
    val title: String = "",
    val titleEnglish: String = "",
    val writer: String = "",
    val manufactureYear: String = "",
    val productClassName: String = "",
    val productStandard: String = "",
    val manageNoYear: String = "",
    val materialTechnic: String = "",
)
