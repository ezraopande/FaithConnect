package com.ezra.newmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezra.newmvvm.data.auth.LoginRequest
import com.ezra.newmvvm.data.auth.LoginResponse
import com.ezra.newmvvm.data.auth.User
import com.ezra.newmvvm.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    fun login(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        val request = LoginRequest(email, password)
        RetrofitInstance.api.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _userId.value = response.body()?.user?.id
                    callback(true, null)
                } else {
                    when (response.code()) {
                        400 -> callback(false, "Invalid email or password.")
                        404 -> callback(false, "Email does not exist.")
                        else -> callback(false, "Unknown error occurred.")
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(false, t.message)
            }
        })
    }

    fun getUserDetails(userId: Int, callback: (User?, String?) -> Unit) {
        RetrofitInstance.api.getUserDetails(userId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Failed to fetch user details")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }
}
