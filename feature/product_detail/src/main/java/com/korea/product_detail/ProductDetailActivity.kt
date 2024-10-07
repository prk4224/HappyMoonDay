package com.korea.product_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.korea.product_detail.databinding.ActivityProductDetailBinding
import com.korea.product_detail.di.ProductDetailComposeInjector
import com.korea.product_detail.model.ProductDetail

internal class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectProductDetailScreen()
    }

    private fun injectProductDetailScreen() = with(ProductDetailComposeInjector()) {
        inject(
            container = binding.containerCv,
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
}
