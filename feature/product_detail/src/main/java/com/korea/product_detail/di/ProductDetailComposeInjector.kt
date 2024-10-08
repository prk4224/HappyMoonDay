package com.korea.product_detail.di

import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.korea.product_detail.ui.ProductDetailScreen
import com.korea.search.domain.model.Artwork

internal class ProductDetailComposeInjector {

    fun inject(
        container: ComposeView,
        artwork: Artwork,
        onClickBack: () -> Unit,
    ) = with(container) {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            ProductDetailScreen(
                artwork = artwork,
                onClickBack = onClickBack
            )
        }
    }
}
