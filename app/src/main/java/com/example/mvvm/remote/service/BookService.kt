package com.example.mvvm.remote.service

import com.example.mvvm.BuildConfig
import com.example.mvvm.model.Book
import com.example.mvvm.remote.ApiConnection
import com.example.mvvm.remote.api.BooksApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookService {
    private val api = ApiConnection().create(BuildConfig.JSON_SERVER, BooksApi::class.java)

    fun fetchBooks(): Observable<MutableList<Book>> {
        return api
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addBook(book: Book): Observable<Unit> {
        return api
            .addBook(book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}