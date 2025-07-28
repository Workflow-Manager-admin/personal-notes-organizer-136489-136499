package com.example.notesfrontend.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesfrontend.api.RetrofitClient
import com.example.notesfrontend.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionViewModel : ViewModel() {
    var token: String? = null
    var isLoggedIn: Boolean = false
        private set
    var loginError: String? = null
    var registerError: String? = null

    // PUBLIC_INTERFACE
    fun login(username: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val call = RetrofitClient.api.login(LoginRequest(username, password))
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body()?.token != null) {
                    token = "Bearer ${response.body()!!.token}"
                    isLoggedIn = true
                    loginError = null
                    onSuccess()
                } else {
                    loginError = "Invalid credentials"
                    onError(loginError!!)
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginError = "Network error"
                onError(loginError!!)
            }
        })
    }

    // PUBLIC_INTERFACE
    fun register(username: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val call = RetrofitClient.api.register(RegisterRequest(username, password))
        call.enqueue(object: Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    registerError = null
                    onSuccess()
                } else {
                    registerError = "User already exists or invalid data"
                    onError(registerError!!)
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                registerError = "Network error"
                onError(registerError!!)
            }
        })
    }

    // PUBLIC_INTERFACE
    fun logout() {
        token = null
        isLoggedIn = false
    }
}
