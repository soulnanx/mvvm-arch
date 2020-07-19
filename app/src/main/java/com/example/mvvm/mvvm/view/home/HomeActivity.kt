package com.example.mvvm.mvvm.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mvvm.R
import com.example.mvvm.databinding.HomeActivityBinding
import com.example.mvvm.dev.BooksActivity
import com.example.mvvm.mvvm.feedback.model.FeedbackModel
import com.example.mvvm.mvvm.feedback.view.FeedbackDialog
import com.example.mvvm.mvvm.feedback.viewModel.FeedbackDialogViewModel
import com.example.mvvm.mvvm.view.PlacesActivity
import com.example.mvvm.mvvm.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var dialog: FeedbackDialog
    private val feedbackViewModel by viewModel<FeedbackDialogViewModel>()
    private val homeViewModel by viewModel<HomeViewModel>()

    private val binding: HomeActivityBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.home_activity
        ) as HomeActivityBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setBinding()
        setFeedbackObservables()
    }

    private fun setFeedbackObservables() {
        feedbackViewModel.onMainAction.observe(this,
            Observer {
                when (it) {
                    FeedbackModel.internalError -> internalErrorAction()
                }
                dialog.dismiss()
            }
        )

        homeViewModel.navigator.observe(this,
            Observer { navigator ->
                when (navigator) {
                    HomeViewModel.HomeNavigator.navigateToAddBook
                    -> navigateToBooks()

                    HomeViewModel.HomeNavigator.navigateToPlaces
                    -> navigateToPlaces()

                    HomeViewModel.HomeNavigator.showDialog
                    -> showDialog()
                }
            }
        )
    }

    private fun internalErrorAction() {
        Toast.makeText(this@HomeActivity, "ok", Toast.LENGTH_SHORT).show()
    }

    private fun setBinding() {
        binding.homeViewModel = homeViewModel
    }

    private fun navigateToPlaces() {
        val intent = Intent(this, PlacesActivity::class.java)
        startActivity(intent)
    }

    private fun showDialog() {
        dialog = FeedbackDialog.newInstance(
            FeedbackModel.internalError

        ).apply {
            show(supportFragmentManager, FeedbackDialog.TAG)
        }
    }

    private fun navigateToBooks() {
        val intent = Intent(this, BooksActivity::class.java)
        startActivity(intent)
    }
}
