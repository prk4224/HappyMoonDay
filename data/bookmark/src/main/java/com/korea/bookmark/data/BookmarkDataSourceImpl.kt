package com.korea.bookmark.data

import com.korea.database.dao.ArtworkDao
import com.korea.database.entity.ArtworkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class BookmarkDataSourceImpl @Inject constructor(
    private val artworkDao: ArtworkDao,
) : BookmarkDataSource {

    override fun fetch(): Flow<List<ArtworkEntity>?> {
        return artworkDao.fetch()
    }
}
