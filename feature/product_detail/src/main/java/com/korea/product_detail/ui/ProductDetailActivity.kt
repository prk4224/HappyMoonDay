package com.korea.product_detail.ui

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.korea.product_detail.databinding.ActivityProductDetailBinding
import com.korea.product_detail.di.ProductDetailComposeInjector
import com.korea.search.domain.model.Artwork

internal class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private val artwork by lazy {
        intent.parcelable(ARTWORK) ?: Artwork()
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
            artwork = artwork,
            onClickBack = ::onClickBack
        )

    }

    private fun onClickBack() {
        finish()
    }

    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    companion object {
        private const val ARTWORK = "artwork"
    }
}
