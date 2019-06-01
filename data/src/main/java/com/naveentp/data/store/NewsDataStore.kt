package com.naveentp.data.store

import com.naveentp.shared.NewsDetails
import io.reactivex.Flowable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
interface NewsDataStore {

    fun getTopHeadlines(): Flowable<NewsDetails>
}