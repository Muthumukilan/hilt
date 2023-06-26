package com.example.hilt.ui.theme.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String,
    val discription:String
)
