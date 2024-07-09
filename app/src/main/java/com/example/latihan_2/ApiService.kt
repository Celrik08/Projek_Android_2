package com.example.latihan_2

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUser(): Call<UsersResponse>
}