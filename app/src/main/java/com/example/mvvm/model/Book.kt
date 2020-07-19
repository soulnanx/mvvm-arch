package com.example.mvvm.model

class Book(
    var title: String = String(),
    var id: Int = 0
) {

    var idValue = id.toString()

    companion object{
        val empty = Book()
    }
}