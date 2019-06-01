package com.naveentp.data.store

import com.naveentp.data.repository.NewsCache
import com.naveentp.shared.NewsDetails
import io.reactivex.Flowable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
class NewsCacheDataStore(
    private val newsCache: NewsCache
) : NewsDataStore {

    override fun getTopHeadlines(): Flowable<NewsDetails> {
        return newsCache.getTopHeadlines()
    }
}