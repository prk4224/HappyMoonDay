package com.korea.bookmark.di

import com.korea.bookmark.data.BookmarkDataSource
import com.korea.bookmark.data.BookmarkDataSourceImpl
import com.korea.bookmark.data.BookmarkRepositoryImpl
import com.korea.bookmark.domain.BookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface BookmarkModule {

    @Binds
    fun bindBookmarkDataSource(bookmarkDataSourceImpl: BookmarkDataSourceImpl): BookmarkDataSource

    @Binds
    fun bindBookmarkRepository(bookmarkRepositoryImpl: BookmarkRepositoryImpl): BookmarkRepository
}
