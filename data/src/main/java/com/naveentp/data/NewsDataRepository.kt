package com.naveentp.data

import com.naveentp.data.repository.NewsCache
import com.naveentp.data.store.NewsDataStoreFactory
import com.naveentp.domain.repository.NewsRepository
import com.naveentp.shared.NewsDetails
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * @author Naveen T P
 * @since 01/06/19
 */
class NewsDataRepository(
    private val newsCache: NewsCache,
    private val newsDataStoreFactory: NewsDataStoreFactory,
    private val isNetworkAvailable: Boolean
) : NewsRepository {

    override fun getTopHeadLines(): Observable<NewsDetails> {

        return Observable.zip(Observable.just(isNetworkAvailable), newsCache.areArticlesCached().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { network, areCached ->
                Pair(network, areCached)
            })
            .flatMap { pair ->
                newsDataStoreFactory.getDataStore(pair.first, pair.second).getTopHeadlines()
            }.flatMap { newsDetails ->
                newsDataStoreFactory.getCacheDataStore().saveTopHeadlines(newsDetails)
                    .andThen(Observable.just(newsDetails))
            }
    }
}