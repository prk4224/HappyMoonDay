package com.korea.product_detail.data

import com.korea.database.entity.ArtworkEntity
import kotlinx.coroutines.flow.Flow

internal interface ProductDetailDataSource {

    suspend fun insert(info: ArtworkEntity)

    suspend fun delete(info: ArtworkEntity)

    fun isExists(imageUrl: String): Flow<Boolean>
}
