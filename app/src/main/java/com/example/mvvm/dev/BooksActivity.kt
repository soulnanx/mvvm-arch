package com.example.mvvm.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mvvm.R
import com.example.mvvm.databinding.BooksActivityBinding
import com.example.mvvm.model.Book
import com.example.mvvm.mvvm.adapters.BooksAdapter
import com.example.mvvm.mvvm.viewModel.BooksViewModel
import org.koin.android.ext.android.inject

class BooksActivity : AppCompatActivity() {

    private val booksViewModel: BooksViewModel by inject()
    private val booksAdapter: BooksAdapter = BooksAdapter(::onClick)

    private val binding: BooksActivityBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.books_activity
        ) as BooksActivityBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        setBinding()
        setBooksObservable()
        fetchBooks()
    }

    private fun fetchBooks() {
        booksViewModel.fetchBooks()
    }

    private fun setBinding() {
        binding.viewModel = booksViewModel
    }

    private fun setBooksObservable() {
        booksViewModel.onLoading.observe(
            this,
            Observer {
                val label = if (it) "inicio" else "fim"
                Toast.makeText(this, label, Toast.LENGTH_SHORT).show()
            }
        )

        booksViewModel.onFetchBooks.observe(
            this,
            Observer {
                booksAdapter.addAll(it)
                binding.booksRv.adapter = booksAdapter
            }
        )

        booksViewModel.onError.observe(
            this,
            Observer {
                Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show()
            }
        )

        booksViewModel.onAddBook.observe(
            this,
            Observer {
                Toast.makeText(this, "the book ${it.title} was added!", Toast.LENGTH_SHORT).show()
                fetchBooks()
            }
        )

        booksViewModel.onFormError.observe(
            this,
            Observer {
                binding.bookNameTv.error = "mandatory field"
            }
        )
    }

    private fun onClick(book: Book){

    }
}
