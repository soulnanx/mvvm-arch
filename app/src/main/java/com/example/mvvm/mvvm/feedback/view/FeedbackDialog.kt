package com.example.mvvm.mvvm.feedback.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mvvm.R
import com.example.mvvm.databinding.FeedbackDialogBinding
import com.example.mvvm.mvvm.feedback.model.FeedbackModel
import com.example.mvvm.mvvm.feedback.viewModel.FeedbackDialogViewModel


class FeedbackDialog() : DialogFragment() {

    private val feedbackDialogViewModel by activityViewModels<FeedbackDialogViewModel>()

    companion object{
        const val TAG = "FeedbackDialog"

        fun newInstance(model: FeedbackModel) = FeedbackDialog().apply {
            arguments = Bundle().apply {
                putSerializable("model", model)
            }
        }
    }

    private lateinit var binding: FeedbackDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            manager
                .beginTransaction()
                .add(this, tag)
                .commit()
        } catch (e: IllegalStateException) {
            e.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate<FeedbackDialogBinding>(inflater, R.layout.feedback_dialog, container, false)
        init()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
            dialog.setCancelable(false)

        }
    }

    private fun init(){
        setBinding()
        setObservables()
    }

    private fun setObservables() {

        activity?.let {
            feedbackDialogViewModel.onMainAction.observe(it,
                Observer {
//                    dismiss()
                }
            )

            feedbackDialogViewModel.onSecondaryAction.observe(it,
                Observer {
//                    dismiss()
                }
            )
        }
    }

    private fun setBinding() {
        arguments?.let {
            feedbackDialogViewModel.model = it.getSerializable("model") as FeedbackModel
            binding.viewModel = feedbackDialogViewModel
        } ?: kotlin.run {
            dismiss()
        }
    }

}