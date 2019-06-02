package com.naveentp.data.repository

import com.naveentp.shared.NewsDetails
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author Naveen T P
 * @since 01/06/19
 */
interface NewsCache {

    fun getTopHeadlines(): Observable<NewsDetails>

    fun saveTopHeadlines(newsDetails: NewsDetails): Completable

    fun deleteTopHeadlines(): Completable

    fun areArticlesCached(): Single<Boolean>
}