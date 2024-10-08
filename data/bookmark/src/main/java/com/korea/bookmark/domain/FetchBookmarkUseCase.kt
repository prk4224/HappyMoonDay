package com.korea.bookmark.domain

import com.korea.bookmark.model.BookmarkArtwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(): Flow<List<BookmarkArtwork>> {
        return bookmarkRepository.fetch()
    }
}
