package com.korea.database.di

import android.content.Context
import androidx.room.Room
import com.korea.database.HappyMuseumDataBase
import com.korea.database.dao.ArtworkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataBaseModule {

    @Singleton
    @Provides
    fun provideHappyMuseumDataBase(
        @ApplicationContext context: Context,
    ): HappyMuseumDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = HappyMuseumDataBase::class.java,
            name = HappyMuseumDataBase.HAPPY_MUSEUM_APP_NAME,
        ).build()
    }

    @Singleton
    @Provides
    fun provideArtworkDao(
        dataBase: HappyMuseumDataBase,
    ): ArtworkDao = dataBase.artworkDao()
}
