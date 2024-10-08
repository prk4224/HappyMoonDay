package com.korea.product_detail.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.korea.common.constants.IntentKeyConstants.ARTWORK
import com.korea.common.utils.IntentUtils.parcelable
import com.korea.product_detail.databinding.ActivityProductDetailBinding
import com.korea.product_detail.di.ProductDetailComposeInjector
import com.korea.product_detail.model.ProductDetailArtwork
import com.korea.search.domain.model.SearchArtwork
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private val searchArtwork by lazy {
        intent.parcelable(ARTWORK) ?: SearchArtwork()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectProductDetailScreen()
    }

    private fun injectProductDetailScreen() = with(ProductDetailComposeInjector()) {
        inject(
            container = binding.containerCv,
            productDetailArtwork = searchArtwork.convertToProductDetail(),
            onClickBack = ::goBack,
        )
    }

    private fun goBack() {
        finish()
    }

    private fun SearchArtwork.convertToProductDetail(): ProductDetailArtwork {
        return ProductDetailArtwork(
            imageUrl = imageUrl,
            title = title,
            titleEnglish = titleEnglish,
            writer = writer,
            manufactureYear = manufactureYear,
            productClassName = productClassName,
            productStandard = productStandard,
            manageNoYear = manageNoYear,
            materialTechnic = materialTechnic
        )
    }
}
