package com.example.mvvm.di

import com.example.mvvm.mvvm.feedback.viewModel.FeedbackDialogViewModel
import com.example.mvvm.mvvm.viewModel.BooksViewModel
import com.example.mvvm.mvvm.viewModel.CountriesViewModel
import com.example.mvvm.mvvm.viewModel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { CountriesViewModel() }
    viewModel { BooksViewModel() }
    viewModel { FeedbackDialogViewModel() }
    viewModel { HomeViewModel() }
}