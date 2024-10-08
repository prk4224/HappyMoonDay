package com.korea.bookmark.domain

import com.korea.bookmark.model.BookmarkArtwork
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun fetch(): Flow<List<BookmarkArtwork>>
}
