package com.naveentp.domain

import com.naveentp.shared.NewsDetails
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * @author Naveen T P
 * @since 08/06/19
 */
object NewsDataFactory {

    fun randomUUID(): String = UUID.randomUUID().toString()

    fun makeNewsDetails(): NewsDetails {
        return NewsDetails(articles = makeArticlesList(2))
    }

    fun makeArticlesList(count: Int): List<NewsDetails.Article> {
        val articles = mutableListOf<NewsDetails.Article>()
        repeat(count) {
            articles.add(makeArticle())
        }
        return articles
    }

    fun makeArticle(): NewsDetails.Article {
        return NewsDetails.Article(
            randomUUID(), randomUUID(), randomUUID(),
            randomUUID(), randomUUID(), randomUUID(),
            randomUUID()
        )
    }

    fun immediateScheduler(): Scheduler {
        return object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(
                    Executor { it.run() })
            }
        }
    }
}
