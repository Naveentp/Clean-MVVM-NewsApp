package com.naveentp.newsapp

import android.app.Application
import com.naveentp.newsapp.di.modulesList
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author Naveen T P
 * @since 02/06/19
 */
class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        injectKoin()
    }

    private fun injectKoin() {
        startKoin {
            androidContext(this@NewsApp)
            androidLogger()
            modules(modulesList)
        }
    }
}