package com.queensuber.hackherstarterapp.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.queensuber.hackherstarterapp.data.NewsRepository
import com.queensuber.hackherstarterapp.data.NewsResponse
import com.queensuber.hackherstarterapp.data.Resource
import kotlinx.coroutines.launch

class SearchViewModel
@ViewModelInject internal constructor(private val newsRepository: NewsRepository) :
    ViewModel() {
    private val _response = MutableLiveData<Resource<NewsResponse>>()

    val response: LiveData<Resource<NewsResponse>>
        get() = _response

    fun search(query: String) {
        searchNews(query)
    }

    private fun searchNews(query: String) = viewModelScope.launch {
        _response.postValue(Resource.loading(null))
        newsRepository.searchNews(query).let { response ->
            if (response.isSuccessful) {
                _response.postValue(Resource.success(response.body()))
            } else {
                _response.postValue(
                    Resource.error(
                        response.errorBody().toString(),
                        null
                    )
                )
            }
        }
    }
}