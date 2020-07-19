package com.example.mvvm.mvvm.feedback.model

import com.example.mvvm.R
import java.io.Serializable

class FeedbackStyleButton(
    val textColorRes: Int = R.color.blue_royal,
    val backgroundColorRes: Int = android.R.color.transparent,
    val strokeColorRes: Int = R.color.blue_royal
): Serializable {

    companion object {

        val feedbackButtonEdenRed: FeedbackStyleButton by lazy {
            FeedbackStyleButton(
               textColorRes =  R.color.white,
                backgroundColorRes = R.color.red,
                strokeColorRes = R.color.red
            )
        }
    }
}