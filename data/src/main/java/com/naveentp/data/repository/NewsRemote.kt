package com.naveentp.data.repository

import com.naveentp.shared.NewsDetails
import io.reactivex.Observable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
interface NewsRemote {

    fun getTopHeadlines() : Observable<NewsDetails>
}