package com.korea.product_detail.di

import com.korea.product_detail.data.ProductDetailDataSource
import com.korea.product_detail.data.ProductDetailDataSourceImpl
import com.korea.product_detail.data.ProductDetailRepositoryImpl
import com.korea.product_detail.domain.ProductDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface ProductModule {
    @Binds
    fun bindProductDetailDataSource(productDetailDataSourceImpl: ProductDetailDataSourceImpl): ProductDetailDataSource

    @Binds
    fun bindProductDetailRepository(productDetailRepositoryImpl: ProductDetailRepositoryImpl): ProductDetailRepository
}
