package com.korea.product_detail.domain

import com.korea.product_detail.model.ProductDetailArtwork
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {

    suspend fun insert(info: ProductDetailArtwork)

    suspend fun delete(info: ProductDetailArtwork)

    fun isExists(imageUrl: String): Flow<Boolean>
}
