package com.korea.search.di

import com.korea.search.data.SearchDataSource
import com.korea.search.data.SearchDataSourceImpl
import com.korea.search.data.SearchRepositoryImpl
import com.korea.search.domain.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
internal interface SearchModule {

    @Binds
    fun bindSearchDataSource(searchDataSourceImpl: SearchDataSourceImpl): SearchDataSource

    @Binds
    fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}
