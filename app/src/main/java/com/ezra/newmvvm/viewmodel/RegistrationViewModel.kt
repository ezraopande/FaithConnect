package com.ezra.newmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezra.newmvvm.data.auth.UserRegistrationRequest
import com.ezra.newmvvm.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.ezra.newmvvm.ui.theme.auth.Gender
import kotlinx.coroutines.launch


class RegistrationViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _firstName = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName

    private val _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName

    private val _gender = MutableStateFlow(Gender.MALE)
    val gender: StateFlow<Gender> = _gender

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onFirstNameChange(newFirstName: String) {
        _firstName.value = newFirstName
    }

    fun onLastNameChange(newLastName: String) {
        _lastName.value = newLastName
    }

    fun onGenderChange(newGender: Gender) {
        _gender.value = newGender
    }

    fun registerUser(onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val request = UserRegistrationRequest(
                    email = email.value,
                    password = password.value,
                    first_name = firstName.value,
                    last_name = lastName.value,
                    gender = gender.value.value.toString()  // Convert gender enum value to string
                )
                val response = RetrofitInstance.api.registerUser(request)
                if (response.isSuccessful) {
                    _errorMessage.value = null
                    onSuccess()
                } else {
                    _errorMessage.value = "Email already registered"
                    onError()
                }
            } catch (e: Exception) {
                _errorMessage.value = "Registration failed: ${e.message}"
                onError()
            }
        }
    }
}