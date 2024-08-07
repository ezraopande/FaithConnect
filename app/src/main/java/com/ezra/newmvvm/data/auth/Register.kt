package com.ezra.newmvvm.data.auth

data class UserRegistrationRequest(
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val gender: String?
)

data class UserRegistrationResponse(
    val last_login: String?,
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val gender: String?,
    val is_staff: Boolean,
    val is_superuser: Boolean,
    val is_active: Boolean
)
