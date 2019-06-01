package com.naveentp.data.repository

import com.naveentp.shared.NewsDetails
import io.reactivex.Flowable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
interface NewsRemote {

    fun getTopHeadlines() : Flowable<NewsDetails>
}