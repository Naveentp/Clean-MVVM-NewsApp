package com.naveentp.domain

import com.naveentp.domain.repository.NewsRepository
import com.naveentp.domain.scheduler.SchedulerProvider
import com.naveentp.domain.usecase.NewsDetailsUseCase
import com.naveentp.shared.NewsDetails
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

/**
 * @author Naveen T P
 * @since 08/06/19
 */

open class NewsDetailsUseCaseTest {

    private val newsRepository = mock<NewsRepository>()
    private lateinit var schedulerProvider: SchedulerProvider.Factory
    private lateinit var newsDetailsUseCase: NewsDetailsUseCase

    @Before
    fun setup() {
        val immediate = NewsDataFactory.immediateScheduler()
        schedulerProvider = SchedulerProvider.Factory(immediate, immediate)
        newsDetailsUseCase = NewsDetailsUseCase(newsRepository, schedulerProvider)
    }

    @Test
    fun `getTopHeadLines() gets the data`() {
        val newsDetails = NewsDataFactory.makeNewsDetails()
        stubGetTopHeadlines(Observable.just(newsDetails))
        val testObserver = newsDetailsUseCase.getTopHeadlines().test()
        testObserver.assertValue(newsDetails)
    }

    @Test
    fun `getTopHeadLines() call completes`() {
        stubGetTopHeadlines(Observable.just(NewsDataFactory.makeNewsDetails()))
        val testObserver = newsDetailsUseCase.getTopHeadlines().test()
        testObserver.assertComplete()
    }

    @Test
    fun `getTopHeadLines() call fails`() {
        val throwable = Throwable()
        stubGetTopHeadlines(Observable.error(throwable))
        val testObserver = newsDetailsUseCase.getTopHeadlines().test()
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
        testObserver.assertError(throwable)
        testObserver.assertValueCount(0)
    }

    private fun stubGetTopHeadlines(observable: Observable<NewsDetails>) {
        whenever(newsRepository.getTopHeadLines())
            .thenReturn(observable)
    }
}