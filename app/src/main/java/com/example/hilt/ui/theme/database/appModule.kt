package com.example.hilt.ui.theme.database

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class appModule {
    @Singleton
    @Provides
    fun provideNoteRepo(noteDao: NoteDao):NoteRepo{
        return NoteRepo(noteDao)
    }

    @Singleton
    @Provides
    fun provideNoteDB(app:Application):NoteDatabase{
        return NoteDatabase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideDao(noteDatabase: NoteDatabase):NoteDao{
        return noteDatabase.noteDao()
    }
}