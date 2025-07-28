package com.example.notesfrontend.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesfrontend.api.RetrofitClient
import com.example.notesfrontend.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesViewModel : ViewModel() {
    var notes: List<Note> = emptyList()
    var currentNote: Note? = null
    var error: String? = null

    // PUBLIC_INTERFACE
    fun fetchNotes(token: String, onFetched: () -> Unit, onError: (String) -> Unit) {
        val call = RetrofitClient.api.getNotes(token)
        call.enqueue(object: Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                if (response.isSuccessful) {
                    notes = response.body() ?: emptyList()
                    error = null
                    onFetched()
                } else {
                    error = "Failed to fetch notes"
                    onError(error!!)
                }
            }
            override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                error = "Network error"
                onError(error!!)
            }
        })
    }

    // PUBLIC_INTERFACE
    fun fetchNote(token: String, noteId: String, onFetched: () -> Unit, onError: (String) -> Unit) {
        val call = RetrofitClient.api.getNote(token, noteId)
        call.enqueue(object: Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                if (response.isSuccessful) {
                    currentNote = response.body()
                    error = null
                    onFetched()
                } else {
                    error = "Note not found"
                    onError(error!!)
                }
            }
            override fun onFailure(call: Call<Note>, t: Throwable) {
                error = "Network error"
                onError(error!!)
            }
        })
    }

    // PUBLIC_INTERFACE
    fun createNote(token: String, title: String, content: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val call = RetrofitClient.api.createNote(token, NoteBody(title, content))
        call.enqueue(object: Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                if (response.isSuccessful) {
                    error = null
                    onSuccess()
                } else {
                    error = "Failed to create note"
                    onError(error!!)
                }
            }
            override fun onFailure(call: Call<Note>, t: Throwable) {
                error = "Network error"
                onError(error!!)
            }
        })
    }

    // PUBLIC_INTERFACE
    fun updateNote(token: String, noteId: String, title: String, content: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val call = RetrofitClient.api.updateNote(token, noteId, NoteBody(title, content))
        call.enqueue(object: Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                if (response.isSuccessful) {
                    error = null
                    onSuccess()
                } else {
                    error = "Failed to update note"
                    onError(error!!)
                }
            }
            override fun onFailure(call: Call<Note>, t: Throwable) {
                error = "Network error"
                onError(error!!)
            }
        })
    }

    // PUBLIC_INTERFACE
    fun deleteNote(token: String, noteId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val call = RetrofitClient.api.deleteNote(token, noteId)
        call.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    error = null
                    onSuccess()
                } else {
                    error = "Failed to delete note"
                    onError(error!!)
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                error = "Network error"
                onError(error!!)
            }
        })
    }
}
