package com.example.mvvm.remote.repository

import com.example.mvvm.model.Country
import com.example.mvvm.remote.service.CountryService
import io.reactivex.disposables.Disposable

class CountryRepository(){
    
    fun fetchCountries(
        onSuccess: (MutableList<Country>) -> Unit,
        onFailure: (Throwable) -> Unit
    ): Disposable{
        return CountryService()
            .fetchCountries()
            .subscribe(
                onSuccess,
                onFailure
            )
    }
    
}