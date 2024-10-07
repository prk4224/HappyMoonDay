package com.korea.product_detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korea.product_detail.R
import com.korea.product_detail.model.ProductDetail

@Composable
internal fun ProductDetailContent(
    modifier: Modifier = Modifier,
    productDetail: ProductDetail,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = productDetail.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = productDetail.titleEnglish,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        Spacer(Modifier.height(16.dp))

        ContentItem(
            modifier = Modifier
                .weight(1F),
            title = stringResource(R.string.writer),
            content = productDetail.writer
        )

        ContentItem(
            modifier = Modifier
                .weight(1F),
            title = stringResource(R.string.manufacture_year),
            content = "${productDetail.manufactureYear}년"
        )

        ContentItem(
            modifier = Modifier
                .weight(1F),
            title = stringResource(R.string.product_class_name),
            content = productDetail.productClassName
        )

        ContentItem(
            modifier = Modifier
                .weight(1F),
            title = stringResource(R.string.product_standard),
            content = productDetail.productStandard
        )

        ContentItem(
            modifier = Modifier
                .weight(1F),
            title = stringResource(R.string.manage_no_year),
            content = productDetail.manageNoYear
        )

        ContentItem(
            modifier = Modifier
                .weight(1F),
            title = stringResource(R.string.material_technic),
            content = productDetail.materialTechnic
        )
    }
}

@Composable
private fun ContentItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.Gray
        )

        Text(
            modifier = Modifier
                .weight(1F),
            text = content,
            textAlign = TextAlign.End,
            color = Color.Gray
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xffffffff,
    heightDp = 300
)
@Composable
fun PreViewProductDetailContent() {
    ProductDetailContent(
        productDetail = ProductDetail(
            imageUrl = "https://collections.eseoul.go.kr/common/file/getImage.do?size=700&fileSeq=FILE_0000054019-8858",
            title = "꿈은 이루어진다.",
            titleEnglish = "Dreams come ture",
            writer = "홍연화",
            manufactureYear = "2023",
            productClassName = "드로잉&판화",
            productStandard = "300x300cm",
            manageNoYear = "2000",
            materialTechnic = "캔버스에 유채"
        )
    )
}
