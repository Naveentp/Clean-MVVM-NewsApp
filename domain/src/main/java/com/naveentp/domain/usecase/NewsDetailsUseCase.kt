package com.naveentp.domain.usecase

import com.naveentp.domain.repository.NewsRepository
import com.naveentp.domain.scheduler.SchedulerProvider
import com.naveentp.shared.NewsDetails
import io.reactivex.Observable

/**
 * @author Naveen T P
 * @since 01/06/19
 */
open class NewsDetailsUseCase(
    private val newsRepository: NewsRepository,
    private val schedulerProvider: SchedulerProvider.Factory
) {

    fun getTopHeadlines(): Observable<NewsDetails> {
        return newsRepository.getTopHeadLines()
            .compose(schedulerProvider.create())
    }
}