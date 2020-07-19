package com.example.mvvm.remote.service

import com.example.mvvm.BuildConfig
import com.example.mvvm.model.Country
import com.example.mvvm.remote.ApiConnection
import com.example.mvvm.remote.api.CountriesApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CountryService {
    private val api = ApiConnection().create(BuildConfig.JSON_SERVER, CountriesApi::class.java)

    fun fetchCountries(): Observable<MutableList<Country>> {
        return api
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}