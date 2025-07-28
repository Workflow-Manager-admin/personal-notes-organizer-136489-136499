package com.example.notesfrontend.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notesfrontend.ui.screens.*
import com.example.notesfrontend.viewmodel.SessionViewModel
import com.example.notesfrontend.viewmodel.NotesViewModel

// PUBLIC_INTERFACE
@Composable
fun AppNavigation(
    navController: NavHostController,
    sessionViewModel: SessionViewModel,
    notesViewModel: NotesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = if (sessionViewModel.isLoggedIn) "notes" else "login"
    ) {
        composable("login") {
            LoginScreen(
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("notes") { popUpTo("login") { inclusive = true } } },
                sessionViewModel = sessionViewModel
            )
        }
        composable("register") {
            RegisterScreen(
                onLoginClick = { navController.popBackStack() },
                onRegisterSuccess = { navController.navigate("notes") { popUpTo("register") { inclusive = true } } },
                sessionViewModel = sessionViewModel
            )
        }
        composable("notes") {
            NotesListScreen(
                onNoteClick = { noteId -> navController.navigate("noteDetail/$noteId") },
                onAddClick = { navController.navigate("noteCreate") },
                onLogout = {
                    sessionViewModel.logout()
                    navController.navigate("login") { popUpTo("notes") { inclusive = true } }
                },
                sessionViewModel = sessionViewModel,
                notesViewModel = notesViewModel
            )
        }
        composable("noteDetail/{noteId}") { backStackEntry ->
            NoteDetailScreen(
                noteId = backStackEntry.arguments?.getString("noteId") ?: "",
                onEditClick = { navController.navigate("noteEdit/${backStackEntry.arguments?.getString("noteId")}") },
                onBack = { navController.popBackStack() },
                onDelete = {
                    navController.popBackStack()
                },
                sessionViewModel = sessionViewModel,
                notesViewModel = notesViewModel
            )
        }
        composable("noteCreate") {
            NoteEditScreen(
                noteId = null,
                onNoteSaved = { navController.popBackStack() },
                onBack = { navController.popBackStack() },
                sessionViewModel = sessionViewModel,
                notesViewModel = notesViewModel
            )
        }
        composable("noteEdit/{noteId}") { backStackEntry ->
            NoteEditScreen(
                noteId = backStackEntry.arguments?.getString("noteId"),
                onNoteSaved = { navController.popBackStack("noteDetail/${backStackEntry.arguments?.getString("noteId")}", inclusive = false) },
                onBack = { navController.popBackStack() },
                sessionViewModel = sessionViewModel,
                notesViewModel = notesViewModel
            )
        }
    }
}
