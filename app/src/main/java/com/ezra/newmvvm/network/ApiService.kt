package com.ezra.newmvvm.network

import com.ezra.newmvvm.data.Product
import com.ezra.newmvvm.data.auth.LoginRequest
import com.ezra.newmvvm.data.auth.LoginResponse
import com.ezra.newmvvm.data.auth.User
import com.ezra.newmvvm.data.auth.UserRegistrationRequest
import com.ezra.newmvvm.data.auth.UserRegistrationResponse
import com.ezra.newmvvm.data.branches.Branch
import com.ezra.newmvvm.data.sermons.PastorResponse
import com.ezra.newmvvm.data.sermons.SermonResponse
import com.ezra.newmvvm.data.slider.SliderResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("api/products/")
    suspend fun getProducts(): List<Product>

    @GET("api/products/{id}")
    suspend fun getProductById(@Path("id") id: String): Product

    @POST("api/accounts/register/")
    suspend fun registerUser(@Body request: UserRegistrationRequest): Response<UserRegistrationResponse>

    @POST("api/accounts/login/")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("api/accounts/users/{id}/")
    fun getUserDetails(@Path("id") userId: Int): Call<User>

    @GET("api/sliders/")
    suspend fun getSliders(): List<SliderResponse>

    @GET("branches/")
    suspend fun getBranches(): List<Branch>

    @GET("sermons/")
    suspend fun getSermons(): List<SermonResponse>

    @GET("pastors/{id}/")
    suspend fun getPastor(@Path("id") id: String): PastorResponse











}