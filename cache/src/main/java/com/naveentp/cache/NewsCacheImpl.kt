package com.naveentp.cache

import com.naveentp.data.repository.NewsCache
import com.naveentp.shared.NewsDetails
import io.reactivex.Flowable

/**
 * @author Naveen T P
 * @since 02/06/19
 */
class NewsCacheImpl : NewsCache {

    override fun getTopHeadlines(): Flowable<NewsDetails> {
        //TODO: Remove dummy values
        return Flowable.just(NewsDetails("", listOf(), 1))
    }
}