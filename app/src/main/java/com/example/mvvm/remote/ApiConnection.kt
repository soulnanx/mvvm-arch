package com.example.mvvm.remote

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConnection: KoinComponent{

    private lateinit var httpClient: OkHttpClient.Builder

    fun <S> create(
        host: String,
        serviceClass: Class<S>
    ): S {
        setClient()
        setLogs()

        return Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(getConverterFactory())
            .client(httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(serviceClass)
    }

    private fun getConverterFactory() =
        GsonConverterFactory.create(GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create())

    private fun setClient() {
        httpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
    }


    private fun setLogs() {
//        if (BuildConfig.DEBUG) {
//            val logging = HttpLoggingInterceptor()
//            logging.level = HttpLoggingInterceptor.Level.BODY
//            httpClient.addInterceptor(logging)
//        }
    }
}