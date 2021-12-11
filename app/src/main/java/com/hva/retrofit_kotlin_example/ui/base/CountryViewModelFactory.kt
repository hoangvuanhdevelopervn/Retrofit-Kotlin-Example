package com.hva.retrofit_kotlin_example.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hva.retrofit_kotlin_example.data.api.ApiHelper
import com.hva.retrofit_kotlin_example.data.repository.CountryRepository
import com.hva.retrofit_kotlin_example.ui.main.viewmodel.CountryViewModel


class CountryViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(CountryRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}
