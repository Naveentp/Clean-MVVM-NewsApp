package com.naveentp.remote.service

import com.naveentp.shared.NewsDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Naveen T P
 * @since 01/06/19
 */
interface NewsService {

    @GET("top-headlines?sources=google-news")
    fun getTopHeadlines(@Query("apiKey") apikey: String): Observable<NewsDetails>
}