package com.example.mvvm.mvvm.feedback.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.component.SingleLiveEvent
import com.example.mvvm.mvvm.feedback.model.FeedbackModel
import com.example.mvvm.mvvm.feedback.navigate.FeedbackNavigator
import com.example.mvvm.mvvm.base.BaseViewModel

class FeedbackDialogViewModel : BaseViewModel() {

    lateinit var model: FeedbackModel
    val onMainAction = MutableLiveData<FeedbackModel>()
    val onSecondaryAction = MutableLiveData<FeedbackModel>()

    fun mainAction(){
        onMainAction.value = model
    }

    fun secondaryAction(){
        onSecondaryAction.value = model
    }

}