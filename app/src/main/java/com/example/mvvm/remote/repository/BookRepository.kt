package com.example.mvvm.remote.repository

import com.example.mvvm.model.Book
import com.example.mvvm.remote.service.BookService
import io.reactivex.disposables.Disposable

class BookRepository(){

    companion object{
        val booksSaved = mutableListOf<Book>()
    }

    
    fun fetchBooks(
        onSuccess: (MutableList<Book>) -> Unit,
        onFailure: (Throwable) -> Unit
    ): Disposable{
        return BookService()
            .fetchBooks()
            .subscribe(
                {
                    it.addAll(booksSaved)
                    onSuccess(it)
                },
                onFailure
            )
    }

    fun addBook(
        book: Book,
        onSuccess: (Book) -> Unit,
        onFailure: (Throwable) -> Unit
    ): Disposable{
        return BookService()
            .addBook(book)
            .subscribe(
                {
                    booksSaved.add(book)
                    onSuccess(book)
                },
                onFailure
            )
    }
    
}