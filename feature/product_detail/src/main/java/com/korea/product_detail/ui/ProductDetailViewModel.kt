package com.korea.product_detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korea.product_detail.domain.DeleteProductDetailUseCase
import com.korea.product_detail.domain.InsertProductDetailUseCase
import com.korea.product_detail.domain.IsExistsProductDetailUseCase
import com.korea.product_detail.model.ProductDetailArtwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val isExistsProductDetailUseCase: IsExistsProductDetailUseCase,
    private val insertProductDetailUseCase: InsertProductDetailUseCase,
    private val deleteProductDetailUseCase: DeleteProductDetailUseCase,
) : ViewModel() {

    private val imageUrl = savedStateHandle.getStateFlow(PRODUCT_DETAIL_ARTWORK, "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val isBookmark: StateFlow<Boolean> = imageUrl.filterNotNull()
        .flatMapLatest { url ->
            isExistsProductDetailUseCase(url)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    fun update(productDetailArtwork: ProductDetailArtwork) {
        if (isBookmark.value) {
            delete(productDetailArtwork)
        } else {
            insert(productDetailArtwork)
        }
    }

    fun updateArtwork(imageUrl: String) {
        savedStateHandle[PRODUCT_DETAIL_ARTWORK] = imageUrl
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

    companion object {
        private const val PRODUCT_DETAIL_ARTWORK = "PRODUCT_DETAIL_ARTWORK"
    }
}
