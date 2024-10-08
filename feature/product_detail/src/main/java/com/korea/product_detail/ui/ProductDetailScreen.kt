package com.korea.product_detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.korea.product_detail.model.ProductDetailArtwork

@Composable
internal fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    productDetailArtwork: ProductDetailArtwork,
    onClickBack: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val isExists by viewModel.isBookmark.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateBookmarkStatus(productDetailArtwork.imageUrl)
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
                    viewModel.click(productDetailArtwork)
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
    }
}

@Preview
@Composable
private fun PreViewProductDetailScreen() {
    ProductDetailScreen(
        productDetailArtwork = ProductDetailArtwork(
            imageUrl = "https://collections.eseoul.go.kr/common/file/getImage.do?size=700&fileSeq=FILE_0000054019-8858",
            title = "꿈은 이루어진다.",
            titleEnglish = "Dreams come ture",
            writer = "홍연화",
            manufactureYear = "2023",
            productClassName = "드로잉&판화",
            productStandard = "300x300cm",
            manageNoYear = "2000",
            materialTechnic = "캔버스에 유채"
        ),
        onClickBack = { },
    )
}
