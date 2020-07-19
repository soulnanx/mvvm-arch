package com.example.mvvm.mvvm.feedback.viewModel

import com.example.mvvm.mvvm.base.BaseViewModel
import com.example.mvvm.mvvm.feedback.model.FeedbackModel
import com.example.mvvm.mvvm.feedback.navigate.FeedbackNavigator

class FeedbackFragmentViewModel : BaseViewModel() {

    lateinit var model: FeedbackModel
    lateinit var navigator: FeedbackNavigator

    fun navigateTo(){
        navigator.navigateBack()
    }

    fun secondaryAction(){
        navigator.navigateToSecondaryAction()
    }

}