package com.example.notesfrontend.model

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String?
)

data class RegisterRequest(
    val username: String,
    val password: String
)

data class RegisterResponse(
    val message: String?
)

data class Note(
    val _id: String,
    val title: String,
    val content: String,
    val createdAt: String?,
    val updatedAt: String?
)

data class NoteBody(
    val title: String,
    val content: String
)
