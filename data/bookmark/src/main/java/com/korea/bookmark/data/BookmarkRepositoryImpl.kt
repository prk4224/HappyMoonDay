package com.korea.bookmark.data

import com.korea.bookmark.domain.BookmarkRepository
import com.korea.bookmark.model.BookmarkArtwork
import com.korea.database.entity.ArtworkEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDataSource: BookmarkDataSource,
) : BookmarkRepository {

    override fun fetch(): Flow<List<BookmarkArtwork>> {
        return bookmarkDataSource.fetch().map { artworks ->
            artworks?.map { it.convertToDomain() } ?: listOf()
        }
    }

    private fun ArtworkEntity.convertToDomain(): BookmarkArtwork {
        return BookmarkArtwork(
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
