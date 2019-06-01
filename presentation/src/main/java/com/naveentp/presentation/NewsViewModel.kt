package com.naveentp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naveentp.Resource
import com.naveentp.domain.usecase.NewsDetailsUseCase
import com.naveentp.shared.NewsDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

/**
 * @author Naveen T P
 * @since 01/06/19
 */
class NewsDetailsViewModel(
    private val newsDetailsUseCase: NewsDetailsUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun getTopHeadlines(): LiveData<Resource<NewsDetails>> {
        val result = MutableLiveData<Resource<NewsDetails>>()
        result.value = Resource.Loading()

        val disposable = newsDetailsUseCase.getTopHeadlines().subscribe(
            { newsDetails ->
                result.value = Resource.Success(newsDetails)
            },
            { onError ->
                result.value = Resource.Failure(onError)
            }
        )
        disposable.addTo(disposables)

        return result
    }


    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}