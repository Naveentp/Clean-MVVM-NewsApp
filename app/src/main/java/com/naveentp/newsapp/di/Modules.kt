package com.naveentp.newsapp.di

import com.naveentp.NewsRemoteImpl
import com.naveentp.cache.NewsCacheImpl
import com.naveentp.data.NewsDataRepository
import com.naveentp.data.repository.NewsCache
import com.naveentp.data.repository.NewsRemote
import com.naveentp.data.store.NewsCacheDataStore
import com.naveentp.data.store.NewsDataStoreFactory
import com.naveentp.data.store.NewsRemoteDataStore
import com.naveentp.domain.repository.NewsRepository
import com.naveentp.domain.scheduler.SchedulerProvider
import com.naveentp.domain.usecase.NewsDetailsUseCase
import com.naveentp.newsapp.scheduler.AndroidSchedulerProvider
import com.naveentp.newsapp.BuildConfig
import com.naveentp.presentation.NewsDetailsViewModel
import com.naveentp.remote.service.NewsService
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Naveen T P
 * @since 01/06/19
 */

val modulesList by lazy {
    listOf(
        appModule,
        viewModelModule,
        domainModule,
        dataModule,
        cacheModule,
        remoteModule
    )
}
val appModule: Module = module {
    factory<SchedulerProvider.Factory> { AndroidSchedulerProvider() }
}

val viewModelModule: Module = module {
    viewModel { NewsDetailsViewModel(newsDetailsUseCase = get()) }
}

val domainModule: Module = module {
    factory { NewsDetailsUseCase(newsRepository = get(), schedulerProvider = get()) }
}

val dataModule: Module = module {
    single<NewsRepository> { NewsDataRepository(newsDataStoreFactory = get()) }
    single { NewsDataStoreFactory(newsCacheDataStore = get(), newsRemoteDataStore = get()) }
    single { NewsCacheDataStore(newsCache = get()) }
    single { NewsRemoteDataStore(newsRemote = get()) }
}

val cacheModule: Module = module {
    single<NewsCache> { NewsCacheImpl() }
    //TODO: Add Room DI
}

val remoteModule: Module = module {
    single<NewsRemote> { NewsRemoteImpl(newsService = get(), apiKey = BuildConfig.NEWS_API_KEY) }
    single<NewsService> { retrofit.create(NewsService::class.java) }
}

private const val BASE_URL = "https://newsapi.org/v2/"

private val retrofit = createRetrofit(BASE_URL)

private fun createRetrofit(baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .client(OkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}