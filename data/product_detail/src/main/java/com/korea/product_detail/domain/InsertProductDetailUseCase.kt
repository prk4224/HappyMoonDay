package com.korea.product_detail.domain

import com.korea.product_detail.model.ProductDetailArtwork
import javax.inject.Inject

class InsertProductDetailUseCase @Inject constructor(
    private val productDetailRepository: ProductDetailRepository
) {
    suspend operator fun invoke(productDetailArtwork: ProductDetailArtwork) {
        productDetailRepository.insert(productDetailArtwork)
    }
}
