package com.naveentp.newsapp.home

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.naveentp.Resource
import com.naveentp.newsapp.R
import com.naveentp.newsapp.article.Article
import com.naveentp.newsapp.article.ArticleActivity.Companion.start
import com.naveentp.presentation.NewsDetailsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class NewsListActivity : AppCompatActivity() {

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
                    progressBar.visibility = VISIBLE
                }
                is Resource.Success -> {
                    rvNewsList.adapter = NewsListAdapter(it.data.articles) {
                        start(this, Article(it.urlToImage, it.title, it.description))
                    }
                    progressBar.visibility = GONE
                }
                is Resource.Failure -> {
                    Toast.makeText(this, "Failure: ${it.throwable.localizedMessage}", LENGTH_SHORT).show()
                    progressBar.visibility = GONE
                }
            }
        })
    }
}
