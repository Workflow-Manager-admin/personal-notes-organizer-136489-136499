package com.example.notesfrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.notesfrontend.ui.theme.NotesFrontendTheme
import com.example.notesfrontend.ui.navigation.AppNavigation
import com.example.notesfrontend.viewmodel.SessionViewModel
import com.example.notesfrontend.viewmodel.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesFrontendTheme {
                val sessionViewModel: SessionViewModel = viewModel()
                val notesViewModel: NotesViewModel = viewModel()
                val navController = rememberNavController()

                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(
                        navController = navController,
                        sessionViewModel = sessionViewModel,
                        notesViewModel = notesViewModel
                    )
                }
            }
        }
    }
}
