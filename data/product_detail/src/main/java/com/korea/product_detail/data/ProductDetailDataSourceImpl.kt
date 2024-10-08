package com.korea.product_detail.data

import com.korea.database.dao.ArtworkDao
import com.korea.database.entity.ArtworkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ProductDetailDataSourceImpl @Inject constructor(
    private val artworkDao: ArtworkDao,
) : ProductDetailDataSource {

    override suspend fun insert(info: ArtworkEntity) {
        return artworkDao.insert(info)
    }

    override suspend fun delete(info: ArtworkEntity) {
        return artworkDao.delete(info)
    }

    override fun isExists(imageUrl: String): Flow<Boolean> {
        return artworkDao.isExists(imageUrl)
    }
}
