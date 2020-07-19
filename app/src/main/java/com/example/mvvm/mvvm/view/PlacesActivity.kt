package com.example.mvvm.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mvvm.R
import com.example.mvvm.databinding.PlacesActivityBinding
import com.example.mvvm.model.Book
import com.example.mvvm.model.Country
import com.example.mvvm.mvvm.adapters.BooksAdapter
import com.example.mvvm.mvvm.adapters.CountriesAdapter
import com.example.mvvm.mvvm.viewModel.BooksViewModel
import com.example.mvvm.mvvm.viewModel.CountriesViewModel
import org.koin.android.ext.android.inject

class PlacesActivity : AppCompatActivity() {

    private val countriesViewModel: CountriesViewModel by inject()
    private val booksViewModel: BooksViewModel by inject()
    private val countriesAdapter: CountriesAdapter = CountriesAdapter(::onClickCountry)
    private val booksAdapter: BooksAdapter = BooksAdapter(::onClickBook)

    private val binding: PlacesActivityBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.places_activity
        ) as PlacesActivityBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        setCountriesObservable()
        setBooksObservable()
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
    }

    private fun setCountriesObservable() {
        countriesViewModel.onLoadingLiveData.observe(
            this,
            Observer {
                val label = if (it) "inicio" else "fim"
                Toast.makeText(this, label, Toast.LENGTH_SHORT).show()
            }
        )

        countriesViewModel.onResultLiveData.observe(
            this,
            Observer {
                countriesAdapter.addAll(it)
                binding.countriesRv.adapter = countriesAdapter
            }
        )

        countriesViewModel.onErrorLiveData.observe(
            this,
            Observer {
                Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun onClickCountry(country: Country){
        Toast.makeText(this, country.name, Toast.LENGTH_SHORT).show()
    }

    private fun onClickBook(book: Book){
        Toast.makeText(this, book.title, Toast.LENGTH_SHORT).show()
    }
}
