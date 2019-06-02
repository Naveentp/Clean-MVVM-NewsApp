package com.naveentp.newsapp.util

import com.naveentp.domain.scheduler.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Naveen T P
 * @since 01/06/19
 */
class AndroidSchedulerProvider :
    SchedulerProvider.Factory(Schedulers.io(), AndroidSchedulers.mainThread())