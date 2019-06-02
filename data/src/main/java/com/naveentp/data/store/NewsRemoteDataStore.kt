package com.naveentp.data.store

import com.naveentp.data.repository.NewsRemote
import com.naveentp.shared.NewsDetails
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
class NewsRemoteDataStore(
    private val newsRemote: NewsRemote
) : NewsDataStore {

    override fun getTopHeadlines(): Observable<NewsDetails> {
        return newsRemote.getTopHeadlines()
    }

    override fun saveTopHeadlines(newsDetails: NewsDetails): Completable {
        throw UnsupportedOperationException("Not Supported")
    }
}