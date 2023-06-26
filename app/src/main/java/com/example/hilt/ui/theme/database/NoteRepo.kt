package com.example.hilt.ui.theme.database

import kotlinx.coroutines.flow.Flow

class NoteRepo (private val noteDao: NoteDao){
    fun getAllFlow():Flow<List<NoteEntity>> = noteDao.getAll()
    suspend fun insert(noteEntity: NoteEntity)=noteDao.upsert(noteEntity = noteEntity)
    suspend fun delete(noteEntity: NoteEntity) = noteDao.delete(noteEntity)
}