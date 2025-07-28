package com.example.notesfrontend.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.notesfrontend.model.Note
import com.example.notesfrontend.viewmodel.SessionViewModel
import com.example.notesfrontend.viewmodel.NotesViewModel

// (Rest of the code exactly as before)
@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    sessionViewModel: SessionViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorState by remember { mutableStateOf<String?>(null) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(36.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = {
                focusManager.clearFocus()
                sessionViewModel.login(username, password, {
                    errorState = null
                    onLoginSuccess()
                }, { err ->
                    errorState = err
                })
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = username.isNotBlank() && password.isNotBlank()
        ) {
            Text("Log In")
        }
        Spacer(modifier = Modifier.height(14.dp))
        TextButton(onClick = onRegisterClick) {
            Text("Don't have an account? Register")
        }
        if (errorState != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(errorState!!, color = MaterialTheme.colorScheme.error)
        }
    }
}

// The rest of the composable screens should be placed here as previously written.
