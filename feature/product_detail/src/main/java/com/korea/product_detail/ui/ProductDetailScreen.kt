package com.korea.product_detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.korea.product_detail.model.ProductDetailArtwork
import com.korea.product_detail.ui.viewmodel.ProductDetailViewModel

@Composable
internal fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    productDetailArtwork: ProductDetailArtwork,
    onClickBack: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val isExists by viewModel.isBookmark.collectAsStateWithLifecycle()
    var isShowBookmarkDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.observeIsBookmark(productDetailArtwork.imageUrl)
    }

    Surface {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            ProductDetailHeader(
                title = productDetailArtwork.title,
                isBookmark = isExists,
                onClickBack = onClickBack,
                onClickBookmark = {
                    isShowBookmarkDialog = true
                }
            )

            AsyncImage(
                model = productDetailArtwork.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentDescription = null
            )

            ProductDetailContent(
                modifier = Modifier
                    .weight(1F),
                productDetailArtwork = productDetailArtwork
            )
        }

        if(isShowBookmarkDialog) {
            BookmarkDialog(
                isBookmark = isExists,
                onClick = {
                    viewModel.update(productDetailArtwork)
                },
                dismiss = {
                    isShowBookmarkDialog = false
                }
            )
        }
    }
}
