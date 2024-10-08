package com.korea.bookmark.data

import com.korea.database.entity.ArtworkEntity
import kotlinx.coroutines.flow.Flow

internal interface BookmarkDataSource {
    fun fetch(): Flow<List<ArtworkEntity>?>
}
