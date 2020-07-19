package com.example.mvvm.mvvm.viewModel

import androidx.lifecycle.ViewModel
import com.example.mvvm.component.SingleLiveEvent

class HomeViewModel() : ViewModel(){

    val navigator = SingleLiveEvent<HomeNavigator>()

    fun navigateToAddBook(){
        navigator.value = HomeNavigator.navigateToAddBook
    }

    fun navigateToPlaces(){
        navigator.value = HomeNavigator.navigateToPlaces
    }

    fun showDialog(){
        navigator.value = HomeNavigator.showDialog
    }

    enum class HomeNavigator{
        navigateToAddBook,
        navigateToPlaces,
        showDialog
    }

}