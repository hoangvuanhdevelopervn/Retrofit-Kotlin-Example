package com.hva.retrofit_kotlin_example.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hva.retrofit_kotlin_example.data.repository.CountryRepository
import com.hva.retrofit_kotlin_example.utils.Resource
import kotlinx.coroutines.Dispatchers


class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    fun getCountries() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = countryRepository.getCountries()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}