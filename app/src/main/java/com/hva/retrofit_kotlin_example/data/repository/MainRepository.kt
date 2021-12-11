package com.hva.retrofit_kotlin_example.data.repository

import com.hva.retrofit_kotlin_example.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

    suspend fun getCountries() = apiHelper.getCountries()


}