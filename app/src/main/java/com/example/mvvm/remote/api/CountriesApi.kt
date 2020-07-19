package com.example.mvvm.remote.api

import com.example.mvvm.model.Country
import io.reactivex.Observable
import retrofit2.http.GET

interface CountriesApi {

    @GET("countries")
    fun fetchAll(
    ) : Observable<MutableList<Country>>

}