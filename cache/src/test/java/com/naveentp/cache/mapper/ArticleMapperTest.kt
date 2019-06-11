package com.naveentp.cache.mapper

import com.naveentp.cache.CacheTestFactory
import com.naveentp.cache.model.ArticleEntity
import com.naveentp.shared.NewsDetails
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Naveen T P
 * @since 2019-06-11
 */
@RunWith(JUnit4::class)
class ArticleMapperTest {

    private val mapper = ArticlesMapper()

    @Test
    fun `mapToEntity() maps data`() {
        val newsDetails: NewsDetails = CacheTestFactory.makeNewsDetailsWithArticleList(2)
        val entityList: List<ArticleEntity> = mapper.mapToEntity(newsDetails)
        assertEqualsData(newsDetails, entityList)
    }

    @Test
    fun `mapFromEntity() maps data`() {
        val entityList: List<ArticleEntity> = CacheTestFactory.makeArticleEntityList(2)
        val newsDetails: NewsDetails = mapper.mapFromEntity(entityList)
        assertEqualsData(newsDetails, entityList)
    }

    private fun assertEqualsData(newsDetails: NewsDetails, articlesEntity: List<ArticleEntity>) {
        newsDetails.articles.forEachIndexed { index, article ->
            assertEquals(article.author, articlesEntity[index].author)
            assertEquals(article.title, articlesEntity[index].title)
            assertEquals(article.description, articlesEntity[index].description)
            assertEquals(article.url, articlesEntity[index].url)
            assertEquals(article.urlToImage, articlesEntity[index].urlToImage)
            assertEquals(article.publishedAt, articlesEntity[index].publishedAt)
            assertEquals(article.content, articlesEntity[index].content)
        }
    }
}