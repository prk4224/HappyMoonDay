package com.korea.product_detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korea.product_detail.domain.DeleteProductDetailUseCase
import com.korea.product_detail.domain.InsertProductDetailUseCase
import com.korea.product_detail.domain.IsExistsProductDetailUseCase
import com.korea.product_detail.model.ProductDetailArtwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val isExistsProductDetailUseCase: IsExistsProductDetailUseCase,
    private val insertProductDetailUseCase: InsertProductDetailUseCase,
    private val deleteProductDetailUseCase: DeleteProductDetailUseCase,
) : ViewModel() {

    private val _isBookmark = MutableStateFlow(false)
    val isBookmark = _isBookmark.asStateFlow()

    fun observeIsBookmark(imageUrl: String) {
        viewModelScope.launch {
            isExistsProductDetailUseCase(imageUrl).collect { isExists ->
                _isBookmark.value = isExists
            }
        }
    }

    fun update(productDetailArtwork: ProductDetailArtwork) {
        if (isBookmark.value) {
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
