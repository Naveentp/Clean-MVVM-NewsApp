package com.naveentp.domain.scheduler

import io.reactivex.*
import org.reactivestreams.Publisher

/**
 * @author Naveen T P
 * @since 01/06/19
 */
class SchedulerProvider<T>(
    private val subscribingScheduler: Scheduler,
    private val observingScheduler: Scheduler
) : SingleTransformer<T, T>, ObservableTransformer<T, T>, FlowableTransformer<T, T>, MaybeTransformer<T, T> {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(subscribingScheduler)
            .observeOn(observingScheduler)
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(subscribingScheduler)
            .observeOn(observingScheduler)
    }

    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream.subscribeOn(subscribingScheduler)
            .observeOn(observingScheduler)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(subscribingScheduler)
            .observeOn(observingScheduler)
    }

    open class Factory(
        private val subscribingScheduler: Scheduler,
        private val observingScheduler: Scheduler
    ) {
        fun <T> create(): SchedulerProvider<T> {
            return SchedulerProvider(subscribingScheduler, observingScheduler)
        }
    }
}