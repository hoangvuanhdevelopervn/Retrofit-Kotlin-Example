package com.hva.retrofit_kotlin_example.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()

    suspend fun getCountries() = apiService.getCountries()
}