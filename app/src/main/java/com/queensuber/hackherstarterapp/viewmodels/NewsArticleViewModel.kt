package com.queensuber.hackherstarterapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.queensuber.hackherstarterapp.data.NewsRepository
import com.queensuber.hackherstarterapp.data.NewsResponse
import com.queensuber.hackherstarterapp.data.Resource
import kotlinx.coroutines.launch

class NewsArticleViewModel @ViewModelInject internal constructor(private val newsRepository: NewsRepository) :
    ViewModel() {
    private val topHeadlinesResponse = MutableLiveData<Resource<NewsResponse>>()

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() = viewModelScope.launch {
        topHeadlinesResponse.postValue(Resource.loading(null))
        newsRepository.getTopHeadlines().let { response ->
            if (response.isSuccessful) {
                topHeadlinesResponse.postValue(Resource.success(response.body()))
            } else {
                topHeadlinesResponse.postValue(
                    Resource.error(
                        response.errorBody().toString(),
                        null
                    )
                )
            }
        }
    }
}