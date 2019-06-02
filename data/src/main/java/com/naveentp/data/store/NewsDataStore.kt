package com.naveentp.data.store

import com.naveentp.shared.NewsDetails
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
interface NewsDataStore {

    fun getTopHeadlines(): Observable<NewsDetails>

    fun saveTopHeadlines(newsDetails: NewsDetails) : Completable

    fun deleteTopHeadlines() : Completable
}