package com.korea.product_detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.korea.search.domain.model.Artwork

@Composable
internal fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    artwork: Artwork,
    onClickBack: () -> Unit,
) {
    Surface {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            ProductDetailHeader(
                title = "꿈은 이루어진다",
                isBookmark = true,
                onClickBack = onClickBack
            )

            AsyncImage(
                model = artwork.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentDescription = null
            )

            ProductDetailContent(
                modifier = Modifier
                    .weight(1F),
                artwork = artwork
            )
        }
    }
}

@Preview
@Composable
private fun PreViewProductDetailScreen() {
    ProductDetailScreen(
        artwork = Artwork(
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
        onClickBack = { }
    )
}
