package com.example.mvvm.remote.api

import com.example.mvvm.model.Book
import com.example.mvvm.model.Country
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BooksApi {

    @GET("books")
    fun fetchAll(
    ) : Observable<MutableList<Book>>

    @POST("books")
    fun addBook(
        @Body book: Book
    ) : Observable<Unit>

}