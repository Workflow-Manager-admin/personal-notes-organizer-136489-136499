package com.example.notesfrontend.api

import retrofit2.Call
import retrofit2.http.*
import com.example.notesfrontend.model.*

interface ApiService {
    // PUBLIC_INTERFACE
    @POST("/api/login")
    fun login(@Body req: LoginRequest): Call<LoginResponse>

    // PUBLIC_INTERFACE
    @POST("/api/register")
    fun register(@Body req: RegisterRequest): Call<RegisterResponse>

    // PUBLIC_INTERFACE
    @GET("/api/notes")
    fun getNotes(@Header("Authorization") token: String): Call<List<Note>>

    // PUBLIC_INTERFACE
    @GET("/api/notes/{id}")
    fun getNote(@Header("Authorization") token: String, @Path("id") id: String): Call<Note>

    // PUBLIC_INTERFACE
    @POST("/api/notes")
    fun createNote(@Header("Authorization") token: String, @Body note: NoteBody): Call<Note>

    // PUBLIC_INTERFACE
    @PUT("/api/notes/{id}")
    fun updateNote(@Header("Authorization") token: String, @Path("id") id: String, @Body note: NoteBody): Call<Note>

    // PUBLIC_INTERFACE
    @DELETE("/api/notes/{id}")
    fun deleteNote(@Header("Authorization") token: String, @Path("id") id: String): Call<Void>
}
