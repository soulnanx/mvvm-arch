package com.example.mvvm.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.Country
import com.example.mvvm.remote.repository.CountryRepository

class CountriesViewModel() : ViewModel(){

    private val repository: CountryRepository = CountryRepository()

    val onLoadingLiveData = MutableLiveData<Boolean>()
    val onErrorLiveData = MutableLiveData<Boolean>()
    val onResultLiveData = MutableLiveData<MutableList<Country>>()

    init {
        onLoadingLiveData.value = true
        repository.fetchCountries(
            ::onSuccess,
            ::onFailure
        )
    }

    private fun onSuccess(countries: MutableList<Country>){
        onLoadingLiveData.value = false
        onResultLiveData.value = countries
    }

    private fun onFailure(throwable: Throwable){
        onLoadingLiveData.value = false
        onErrorLiveData.value = true
    }

}