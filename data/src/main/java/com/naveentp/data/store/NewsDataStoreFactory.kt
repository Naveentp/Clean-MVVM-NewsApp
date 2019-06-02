package com.naveentp.data.store

/**
 * @author Naveen T P
 * @since 02/06/19
 */
class NewsDataStoreFactory(
    private val newsCacheDataStore: NewsCacheDataStore,
    private val newsRemoteDataStore: NewsRemoteDataStore
) {

    fun getDataStore(isNetworkAvailable: Boolean, projectCached: Boolean): NewsDataStore {
        return if (!isNetworkAvailable && projectCached)
            newsCacheDataStore
        else newsRemoteDataStore
    }

    fun getCacheDataStore(): NewsDataStore {
        return newsCacheDataStore
    }
}