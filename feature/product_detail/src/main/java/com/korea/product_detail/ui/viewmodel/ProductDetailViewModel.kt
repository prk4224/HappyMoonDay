package com.korea.product_detail.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korea.product_detail.domain.DeleteProductDetailUseCase
import com.korea.product_detail.domain.InsertProductDetailUseCase
import com.korea.product_detail.domain.IsExistsProductDetailUseCase
import com.korea.product_detail.model.ProductDetailArtwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val isExistsProductDetailUseCase: IsExistsProductDetailUseCase,
    private val insertProductDetailUseCase: InsertProductDetailUseCase,
    private val deleteProductDetailUseCase: DeleteProductDetailUseCase,
) : ViewModel() {

    fun isBookmark(imageUrl: String): StateFlow<Boolean> = isExistsProductDetailUseCase(imageUrl)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    fun update(productDetailArtwork: ProductDetailArtwork, isBookmark: Boolean) {
        if (isBookmark) {
            delete(productDetailArtwork)
        } else {
            insert(productDetailArtwork)
        }
    }

    private fun insert(productDetailArtwork: ProductDetailArtwork) {
        viewModelScope.launch {
            insertProductDetailUseCase(productDetailArtwork)
        }
    }

    private fun delete(productDetailArtwork: ProductDetailArtwork) {
        viewModelScope.launch {
            deleteProductDetailUseCase(productDetailArtwork)
        }
    }
}
