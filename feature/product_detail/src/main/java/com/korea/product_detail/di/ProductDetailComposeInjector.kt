package com.korea.product_detail.di

import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.korea.product_detail.model.ProductDetailArtwork
import com.korea.product_detail.ui.ProductDetailScreen

internal class ProductDetailComposeInjector {

    fun inject(
        container: ComposeView,
        productDetailArtwork: ProductDetailArtwork,
        onClickBack: () -> Unit,
    ) = with(container) {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            ProductDetailScreen(
                productDetailArtwork = productDetailArtwork,
                onClickBack = onClickBack,
            )
        }
    }
}
