package com.hva.retrofit_kotlin_example.data.repository


import com.hva.retrofit_kotlin_example.data.api.ApiHelper

class CountryRepository(private val apiHelper: ApiHelper) {

    suspend fun getCountries() = apiHelper.getCountries()


}