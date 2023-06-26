package com.example.hilt.ui.theme.database

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


interface ViewModelAbstract{
    val noteListFlow:Flow<List<NoteEntity>>
    fun addNote(noteEntity: NoteEntity)
    fun deleteNote(noteEntity: NoteEntity)
}

@HiltViewModel
class NoteViewModel
@Inject constructor(private val noteRepo: NoteRepo):ViewModel(),ViewModelAbstract
{
    private val ioScope=CoroutineScope(Dispatchers.IO)
    override val noteListFlow: Flow<List<NoteEntity>> = noteRepo.getAllFlow()

    override fun addNote(noteEntity: NoteEntity) {
        ioScope.launch { noteRepo.insert(noteEntity) }
    }

    override fun deleteNote(noteEntity: NoteEntity) {ioScope.launch { noteRepo.delete(noteEntity) }}
}