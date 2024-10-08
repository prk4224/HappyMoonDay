package com.korea.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.korea.database.entity.ArtworkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {

    @Query("SELECT * FROM ARTWORK_LIST_TABLE")
    suspend fun fetch(): List<ArtworkEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(info: ArtworkEntity)

    @Delete
    suspend fun delete(info: ArtworkEntity)

    @Query("SELECT EXISTS(SELECT * FROM ARTWORK_LIST_TABLE WHERE imageUrl = :imageUrl)")
    fun isExists(imageUrl: String): Flow<Boolean>
}
