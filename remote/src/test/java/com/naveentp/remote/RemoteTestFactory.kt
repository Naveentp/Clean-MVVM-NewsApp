package com.naveentp.remote

import com.naveentp.shared.NewsDetails
import java.util.*

/**
 * @author Naveen T P
 * @since 2019-06-09
 */
object RemoteTestFactory {

    fun randomString(): String = UUID.randomUUID().toString()

    fun makeNewsDetails(): NewsDetails {
        return NewsDetails(articles = makeArticlesList(2))
    }

    fun makeArticlesList(count: Int): List<NewsDetails.Article> {
        val articles = mutableListOf<NewsDetails.Article>()
        repeat(count) {
            articles.add(makeArticle())
        }
        return articles
    }

    fun makeArticle(): NewsDetails.Article {
        return NewsDetails.Article(
            randomString(), randomString(), randomString(),
            randomString(), randomString(), randomString(),
            randomString()
        )
    }
}