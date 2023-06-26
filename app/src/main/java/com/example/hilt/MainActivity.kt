package com.example.hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hilt.ui.theme.HiltTheme
import com.example.hilt.ui.theme.database.NoteEntity
import com.example.hilt.ui.theme.database.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val homeViewModel:NoteViewModel by viewModels()

        setContent {
            HiltTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(homeViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(homeViewModel:NoteViewModel) {
    val noteList=homeViewModel.noteListFlow.collectAsState(initial = listOf())
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        var title by remember {
            mutableStateOf("")
        }
        TextField(value = title, onValueChange = { title = it })

        var discription by remember {
            mutableStateOf("")
        }
        TextField(value = discription, onValueChange = { discription = it })

        Button(onClick = {
            homeViewModel.addNote(
                NoteEntity(
                    title = title,
                    discription = discription
                )
            )
        }) {
            Text(text = "Save")
        }
        LazyColumn(){
            items(noteList.value.size){index ->
                val Note=noteList.value[index]
                Text(text = Note.title)
                Text(text = Note.discription)
                IconButton(onClick = { homeViewModel.deleteNote(Note) }) {
                    Icon(Icons.Default.Delete,"delete")
                }
            }
        }
    }
}