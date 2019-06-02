package com.naveentp.cache

import com.naveentp.cache.factory.NewsDatabase
import com.naveentp.cache.mapper.ArticlesMapper
import com.naveentp.data.repository.NewsCache
import com.naveentp.shared.NewsDetails
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author Naveen T P
 * @since 02/06/19
 */
class NewsCacheImpl(
    private val newsDatabase: NewsDatabase,
    private val articlesMapper: ArticlesMapper
) : NewsCache {

    override fun getTopHeadlines(): Observable<NewsDetails> {
        return newsDatabase.articlesDao().getTopHeadlines()
            .map {
                articlesMapper.mapFromEntity(it)
            }.toObservable()
    }

    override fun saveTopHeadlines(newsDetails: NewsDetails): Completable {
        return newsDatabase.articlesDao().saveTopHeadlines(articlesMapper.mapToEntity(newsDetails))

    }

    override fun deleteTopHeadlines(): Completable {
        return newsDatabase.articlesDao().deleteTopHeadlines()
    }

    override fun areArticlesCached(): Single<Boolean> {
        return newsDatabase.articlesDao().getTopHeadlines().isEmpty
            .map { !it }
    }
}