package com.naveentp

import com.naveentp.data.repository.NewsRemote
import com.naveentp.remote.service.NewsService
import com.naveentp.shared.NewsDetails
import io.reactivex.Flowable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
class NewsRemoteImpl(
    private val newsService: NewsService
) : NewsRemote {

    override fun getTopHeadlines(): Flowable<NewsDetails> {
        return newsService.getTopHeadlines()
    }
}