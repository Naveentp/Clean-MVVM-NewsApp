package com.naveentp.newsapp.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.naveentp.Resource
import com.naveentp.newsapp.R
import com.naveentp.presentation.NewsDetailsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class NewsListActivity : AppCompatActivity() {

    private val newsViewModel: NewsDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        triggerGetTopHeadlines()
    }

    private fun setupRecyclerView() {
        rvNewsList.layoutManager = LinearLayoutManager(this)
    }

    private fun triggerGetTopHeadlines() {
        newsViewModel.getTopHeadlines().observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    rvNewsList.adapter = NewsListAdapter(it.data.articles)
                }
                is Resource.Failure -> {
                    Toast.makeText(this, "Failure: ${it.throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
