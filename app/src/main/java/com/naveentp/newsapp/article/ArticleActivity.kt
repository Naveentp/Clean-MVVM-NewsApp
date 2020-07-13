package com.naveentp.newsapp.article

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.naveentp.newsapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    companion object {
        private const val ARTICLE = "article"

        fun start(context: Context, article: Article) {
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(ARTICLE, article)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val article = intent.getParcelableExtra<Article>(ARTICLE)

        Picasso.get().load(article.imageUrl)
            .placeholder(R.drawable.news_placeholder)
            .error(R.drawable.news_placeholder)
            .into(articleImage)

        articleTitle.text = article.title
        description.text = article.description
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}