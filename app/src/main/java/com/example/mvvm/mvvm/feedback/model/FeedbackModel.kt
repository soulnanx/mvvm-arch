package com.example.mvvm.mvvm.feedback.model

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.example.mvvm.App
import com.example.mvvm.R
import org.koin.core.KoinComponent
import java.io.Serializable

class FeedbackModel : KoinComponent, Serializable {
    var title: String
    var message: String
    var image: Int
    var labelBtn: String
    var labelSecondaryBtn: String
    var feedbackStyleButton: FeedbackStyleButton = FeedbackStyleButton()

    constructor (
        _titleRes: Int,
        _messageRes: Int,
        _image: Int,
        _labelBtnRes: Int = 0,
        _labelSecondaryBtnRes: Int = 0,
        _feedbackStyleButton: FeedbackStyleButton = FeedbackStyleButton()
    ) : this(
            _titleRes,
            App.appCtx().getString(_messageRes),
            _image,
            _labelBtnRes,
            _labelSecondaryBtnRes,
            _feedbackStyleButton
    )

    constructor (
        _titleRes: Int,
        _message: String,
        _image: Int,
        _labelBtnRes: Int = 0,
        _labelSecondaryBtnRes: Int = 0,
        _feedbackStyleButton: FeedbackStyleButton = FeedbackStyleButton()
    ) {
        val app = App.appCtx()
        title = app.getString(_titleRes)
        message = _message
        image = _image
        labelBtn = if (_labelBtnRes != 0) {
            app.getString(_labelBtnRes)
        } else {
            String()
        }

        labelSecondaryBtn = if (_labelSecondaryBtnRes != 0) {
            app.getString(_labelSecondaryBtnRes)
        } else {
            String()
        }

        feedbackStyleButton = _feedbackStyleButton
    }

    fun getImageResource(): Drawable {
        return ContextCompat.getDrawable(App.appCtx(), image)!!
    }

    fun secondaryBtnVisibility() = if (labelSecondaryBtn.isEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }

    fun firstBtnVisibility() = if (labelBtn.isEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }

    companion object {

        val internalError: FeedbackModel by lazy {
            FeedbackModel(
                R.string.feedback_title_internal_error,
                R.string.feedback_msg_internal_error,
                R.drawable.img_no_network,
                R.string.feedback_btn_label_internal_error
            )
        }

    }
}
