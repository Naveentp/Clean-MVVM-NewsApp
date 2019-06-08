package com.naveentp.remote.service

import com.naveentp.shared.NewsDetails
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author Naveen T P
 * @since 01/06/19
 */
interface NewsService {

    @GET("top-headlines?sources=google-news")
    fun getTopHeadlines(): Observable<NewsDetails>
}