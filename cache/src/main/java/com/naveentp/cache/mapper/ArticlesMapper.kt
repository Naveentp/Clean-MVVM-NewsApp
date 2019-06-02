package com.naveentp.cache.mapper

import com.naveentp.cache.model.ArticleEntity
import com.naveentp.shared.NewsDetails

/**
 * @author Naveen T P
 * @since 02/06/19
 */
class ArticlesMapper : EntityMapper<List<ArticleEntity>, NewsDetails> {

    override fun mapToEntity(domain: NewsDetails): List<ArticleEntity> {
        val articleEntities = mutableListOf<ArticleEntity>()
        domain.articles.forEach {
            val article = ArticleEntity(
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
                )
            articleEntities.add(article)
        }
        return articleEntities
    }

    override fun mapFromEntity(entity: List<ArticleEntity>): NewsDetails {
        val articles = mutableListOf<NewsDetails.Article>()
        entity.forEach {
            val article = NewsDetails.Article(
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
            articles.add(article)
        }
        return NewsDetails(articles)
    }
}