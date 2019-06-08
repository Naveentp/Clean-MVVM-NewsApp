package com.naveentp.newsapp.di

import android.content.Context
import androidx.room.Room
import com.naveentp.NewsRemoteImpl
import com.naveentp.cache.NewsCacheImpl
import com.naveentp.cache.factory.NewsDatabase
import com.naveentp.cache.mapper.ArticlesMapper
import com.naveentp.data.NewsDataRepository
import com.naveentp.data.repository.NewsCache
import com.naveentp.data.repository.NewsRemote
import com.naveentp.data.store.NewsCacheDataStore
import com.naveentp.data.store.NewsDataStoreFactory
import com.naveentp.data.store.NewsRemoteDataStore
import com.naveentp.domain.repository.NewsRepository
import com.naveentp.domain.scheduler.SchedulerProvider
import com.naveentp.domain.usecase.NewsDetailsUseCase
import com.naveentp.newsapp.BuildConfig
import com.naveentp.newsapp.util.AndroidSchedulerProvider
import com.naveentp.newsapp.util.NetworkUtil
import com.naveentp.presentation.NewsDetailsViewModel
import com.naveentp.remote.service.NewsService
import com.naveentp.util.NewsInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
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
    single<NewsRepository> {
        NewsDataRepository(
            newsDataStoreFactory = get(),
            newsCache = get(),
            isNetworkAvailable = NetworkUtil.isNetworkAvailable(androidApplication())
        )
    }
    single { NewsDataStoreFactory(newsCacheDataStore = get(), newsRemoteDataStore = get()) }
    single { NewsCacheDataStore(newsCache = get()) }
    single { NewsRemoteDataStore(newsRemote = get()) }
}

val cacheModule: Module = module {
    single<NewsCache> { NewsCacheImpl(newsDatabase = get(), articlesMapper = get()) }
    factory { ArticlesMapper() }
    single<NewsDatabase> { createRoomDb(androidApplication()) }
}

val remoteModule: Module = module {
    single<NewsRemote> { NewsRemoteImpl(newsService = get()) }
    single<NewsService> { retrofit.create(NewsService::class.java) }
}


private fun createRoomDb(appContext: Context): NewsDatabase {
    return Room
        .databaseBuilder(appContext, NewsDatabase::class.java, "news_db")
        .build()
}

private const val BASE_URL = "https://newsapi.org/v2/"

private val retrofit = createRetrofit(BASE_URL)

private fun createRetrofit(baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .client(createOkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(NewsInterceptor(BuildConfig.NEWS_API_KEY))
        .build()
}