package com.hva.retrofit_kotlin_example.data.api

import com.hva.retrofit_kotlin_example.data.model.Country
import com.hva.retrofit_kotlin_example.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>


    @GET("countries/iso")
    suspend fun getCountries(): List<Country>

}