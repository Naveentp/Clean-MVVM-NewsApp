package com.naveentp.newsapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @author Naveen T P
 * @since 02/06/19
 */
object NetworkUtil {

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}