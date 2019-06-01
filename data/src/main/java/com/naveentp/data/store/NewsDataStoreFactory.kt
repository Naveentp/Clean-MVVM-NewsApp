package com.naveentp.data.store

/**
 * @author Naveen T P
 * @since 02/06/19
 */
class NewsDataStoreFactory(
    private val newsCacheDataStore: NewsCacheDataStore,
    private val newsRemoteDataStore: NewsRemoteDataStore
) {

    fun getDataStore(): NewsDataStore {
        //TODO: Return data store based on cache expiry
        return newsRemoteDataStore
    }
}