package com.korea.product_detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korea.product_detail.R

@Composable
internal fun ProductDetailHeader(
    modifier: Modifier = Modifier,
    title: String,
    isBookmark: Boolean,
    onClickBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)

    ) {
        Image(
            modifier = Modifier
                .padding(8.dp)
                .size(30.dp)
                .clickable { onClickBack() },
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back_24),
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1F),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )
        }

        val bookmarkIcon = if (isBookmark) {
            R.drawable.ic_star_on
        } else {
            R.drawable.ic_star_off
        }

        Image(
            modifier = Modifier
                .padding(8.dp)
                .size(30.dp),
            imageVector = ImageVector.vectorResource(bookmarkIcon),
            contentDescription = null
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xffffffff
)
@Composable
private fun PreViewProductDetailHeader() {
    ProductDetailHeader(
        title = "꿈은 이루어진다",
        isBookmark = false,
        onClickBack = { }
    )
}
