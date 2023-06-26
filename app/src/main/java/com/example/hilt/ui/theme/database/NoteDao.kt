package com.example.hilt.ui.theme.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("select * from Note")
    fun getAll(): Flow<List<NoteEntity>>

    @Upsert
    suspend fun upsert(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)
}
