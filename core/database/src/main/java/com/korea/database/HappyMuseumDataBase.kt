package com.korea.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.korea.database.dao.ArtworkDao
import com.korea.database.entity.ArtworkEntity

@Database(
    entities = [
        ArtworkEntity::class
    ],
    version = 1
)
internal abstract class HappyMuseumDataBase : RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao

    companion object {
        const val HAPPY_MUSEUM_APP_NAME = "HappyMuseumApp.db"
    }
}
