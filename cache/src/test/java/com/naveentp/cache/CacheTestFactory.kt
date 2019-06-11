package com.naveentp.cache

import com.naveentp.cache.model.ArticleEntity
import com.naveentp.shared.NewsDetails
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * @author Naveen T P
 * @since 2019-06-09
 */
object CacheTestFactory {

    fun randomString(): String = UUID.randomUUID().toString()

    fun randomInt(): Int = ThreadLocalRandom.current().nextInt(0, 1000 + 1)

    fun makeArticleEntity(): ArticleEntity {
        return ArticleEntity(
            randomInt(), randomString(), randomString(), randomString(),
            randomString(), randomString(), randomString(), randomString()
        )
    }

    fun makeArticleEntityList(count: Int): List<ArticleEntity> {
        val articleEntities = mutableListOf<ArticleEntity>()
        repeat(count) {
            articleEntities.add(makeArticleEntity())
        }
        return articleEntities
    }

    fun makeNewsDetailsWithSingleArticle(): NewsDetails {
        return NewsDetails(articles = makeArticles(1))
    }

    fun makeNewsDetailsWithArticleList(count: Int): NewsDetails {
        return NewsDetails(articles = makeArticles(count))
    }

    private fun makeArticles(count: Int): List<NewsDetails.Article> {
        val articles = mutableListOf<NewsDetails.Article>()
        repeat(count) {
            articles.add(
                NewsDetails.Article(
                    randomString(), randomString(), randomString(),
                    randomString(), randomString(), randomString(), randomString()
                )
            )
        }
        return articles
    }
}