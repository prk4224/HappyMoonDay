package com.korea.product_detail.di

import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.korea.product_detail.model.ProductDetail
import com.korea.product_detail.ui.ProductDetailScreen

internal class ProductDetailComposeInjector {

    fun inject(
        container: ComposeView,
        productDetail: ProductDetail,
    ) = with(container) {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            ProductDetailScreen(
                productDetail = productDetail
            )
        }
    }
}
