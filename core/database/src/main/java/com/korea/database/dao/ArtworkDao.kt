package com.korea.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.korea.database.entity.ArtworkEntity

@Dao
interface ArtworkDao {

    @Query("SELECT * FROM ARTWORK_LIST_TABLE WHERE imageUrl == :imageUrl")
    suspend fun fetch(imageUrl: String): List<ArtworkEntity>?

    @Query("SELECT * FROM ARTWORK_LIST_TABLE")
    suspend fun fetchList(): List<ArtworkEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(info: ArtworkEntity)

    @Delete
    suspend fun deleteRecentInfo(info: ArtworkEntity)
}
