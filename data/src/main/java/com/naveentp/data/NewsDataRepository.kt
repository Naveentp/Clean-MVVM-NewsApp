package com.naveentp.data

import com.naveentp.data.store.NewsDataStoreFactory
import com.naveentp.domain.repository.NewsRepository
import com.naveentp.shared.NewsDetails
import io.reactivex.Flowable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
class NewsDataRepository(
    private val newsDataStoreFactory: NewsDataStoreFactory

) : NewsRepository {
    override fun getTopHeadLines(): Flowable<NewsDetails> {
        //TODO: Write logic to fetch from different data sources
        return newsDataStoreFactory.getDataStore().getTopHeadlines()
    }
}