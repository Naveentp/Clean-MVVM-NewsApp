package com.naveentp.remote

import com.naveentp.remote.service.NewsService
import com.naveentp.shared.NewsDetails
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * @author Naveen T P
 * @since 2019-06-09
 */
@RunWith(JUnit4::class)
class NewsRemoteImplTest {

    private val newsService = mock<NewsService>()
    private val newsRemoteImpl = NewsRemoteImpl(newsService)

    @Test
    fun `getTopHeadLines() call completes`() {
        stubGetTopHeadLinesService(Observable.just(RemoteTestFactory.makeNewsDetails()))
        val testObserver = newsRemoteImpl.getTopHeadlines().test()
        testObserver.assertComplete()
    }

    @Test
    fun `getTopHeadLines() gets data`() {
        val newsDetails = RemoteTestFactory.makeNewsDetails()
        stubGetTopHeadLinesService(Observable.just(newsDetails))
        val testObserver = newsRemoteImpl.getTopHeadlines().test()
        testObserver.assertValue(newsDetails)
    }

    @Test
    fun `getTopHeadLines() call fails`() {
        val throwable = Throwable()
        stubGetTopHeadLinesService(Observable.error(throwable))
        val testObserver = newsRemoteImpl.getTopHeadlines().test()
        testObserver.assertNotComplete()
        testObserver.assertError(throwable)
        testObserver.assertValueCount(0)
        testObserver.assertNoValues()
    }

    @Test
    fun `getTopHeadLines() calls server`() {
        val newsDetails = RemoteTestFactory.makeNewsDetails()
        stubGetTopHeadLinesService(Observable.just(newsDetails))
        newsRemoteImpl.getTopHeadlines().test()
        verify(newsService).getTopHeadlines()
    }

    private fun stubGetTopHeadLinesService(observable: Observable<NewsDetails>) {
        whenever(newsService.getTopHeadlines())
            .thenReturn(observable)
    }
}