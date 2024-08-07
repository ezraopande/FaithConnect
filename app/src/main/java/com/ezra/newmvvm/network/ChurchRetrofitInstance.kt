package com.ezra.newmvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChurchRetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://church.apensoftwares.co.ke/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}