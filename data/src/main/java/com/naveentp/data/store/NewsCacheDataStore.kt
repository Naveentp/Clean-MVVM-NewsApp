package com.naveentp.data.store

import com.naveentp.data.repository.NewsCache
import com.naveentp.shared.NewsDetails
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
class NewsCacheDataStore(
    private val newsCache: NewsCache
) : NewsDataStore {

    override fun getTopHeadlines(): Observable<NewsDetails> {
        return newsCache.getTopHeadlines()
    }

    override fun saveTopHeadlines(newsDetails: NewsDetails): Completable {
        return newsCache.saveTopHeadlines(newsDetails)
    }
}