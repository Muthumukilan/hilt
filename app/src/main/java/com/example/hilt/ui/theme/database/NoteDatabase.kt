package com.example.hilt.ui.theme.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDao

    companion object{
        private var INSTANCE:NoteDatabase?=null
        fun getInstance(context: Context):NoteDatabase{
            if (INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context,NoteDatabase::class.java,"NoteDB").fallbackToDestructiveMigration().build()
            }
            return INSTANCE as NoteDatabase
        }
    }
}