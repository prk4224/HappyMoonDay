package com.korea.product_detail.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsExistsProductDetailUseCase @Inject constructor(
    private val productDetailRepository: ProductDetailRepository
) {
     operator fun invoke(imageUrl: String): Flow<Boolean> {
        return productDetailRepository.isExists(imageUrl)
    }
}
