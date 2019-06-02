package com.naveentp.newsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.naveentp.Resource
import com.naveentp.presentation.NewsDetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        triggerGetTopHeadlines()
    }

    private fun triggerGetTopHeadlines() {
        newsViewModel.getTopHeadlines().observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Toast.makeText(this, "Success: ${it.data}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(this, "Failure: ${it.throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
