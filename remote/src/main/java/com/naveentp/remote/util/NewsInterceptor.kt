package com.naveentp.remote.util

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author Naveen T P
 * @since 08/06/19
 */
class NewsInterceptor constructor(private val apiKey: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("apiKey", apiKey).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}