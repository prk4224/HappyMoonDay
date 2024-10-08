package com.korea.product_detail.data

import com.korea.database.entity.ArtworkEntity
import com.korea.product_detail.domain.ProductDetailRepository
import com.korea.product_detail.model.ProductDetailArtwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val productDetailDataSource: ProductDetailDataSource,
) : ProductDetailRepository {

    override suspend fun insert(info: ProductDetailArtwork) {
        productDetailDataSource.insert(info.convertToEntity())
    }

    override suspend fun delete(info: ProductDetailArtwork) {
        productDetailDataSource.delete(info.convertToEntity())
    }

    override fun isExists(imageUrl: String): Flow<Boolean> {
        return productDetailDataSource.isExists(imageUrl)
    }

    private fun ProductDetailArtwork.convertToEntity(): ArtworkEntity {
        return ArtworkEntity(
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

    private fun ArtworkEntity.convertToDomain(): ProductDetailArtwork {
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
