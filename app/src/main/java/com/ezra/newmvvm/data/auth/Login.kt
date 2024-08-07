package com.ezra.newmvvm.data.auth


data class LoginRequest(val email: String, val password: String)

data class LoginResponse(val token: String, val user: User)

data class User(
    val id: Int,
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val last_login: String,
    val created_at: String,
    val updated_at: String,
    val gender: Int,
    val is_staff: Boolean,
    val is_superuser: Boolean,
    val is_active: Boolean
)
