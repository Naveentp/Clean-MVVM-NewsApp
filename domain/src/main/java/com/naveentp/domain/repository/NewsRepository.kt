package com.naveentp.domain.repository

import com.naveentp.shared.NewsDetails
import io.reactivex.Observable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
interface NewsRepository {

    fun getTopHeadLines(): Observable<NewsDetails>
}