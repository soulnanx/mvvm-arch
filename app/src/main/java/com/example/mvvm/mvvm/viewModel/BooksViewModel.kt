package com.example.mvvm.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.Book
import com.example.mvvm.remote.repository.BookRepository

class BooksViewModel() : ViewModel(){

    private val repository: BookRepository = BookRepository()
    val onLoading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<Boolean>()
    val onFormError = MutableLiveData<FormError>()
    val onFetchBooks = MutableLiveData<MutableList<Book>>()
    val onAddBook = MutableLiveData<Book>()
    var model: Book = Book.empty

    fun addBook(){
        if (isValidForm()) {
            onLoading.value = true
            repository.addBook(
                model,
                ::onSuccessAddBook,
                ::onFailure
            )
        }
    }

    fun fetchBooks(){
        onLoading.value = true
        repository.fetchBooks(
            ::onSuccessFetchBook,
            ::onFailure
        )
    }

    private fun isValidForm(): Boolean{
        if (model.title.isEmpty()) onFormError.value = FormError.EMPTY_TITLE

        return model.title.isNotEmpty()
    }

    private fun onSuccessAddBook(bookAdded: Book){
        onLoading.value = false
        onAddBook.value = bookAdded
    }

    private fun onSuccessFetchBook(books: MutableList<Book>){
        onLoading.value = false
        onFetchBooks.value = books
    }

    private fun onFailure(throwable: Throwable){
        onLoading.value = false
        onError.value = true
    }

    enum class FormError{
        EMPTY_TITLE,
        TITLE_TOO_LONG
    }

}