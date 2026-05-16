package com.example.newsproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.data.model.Article
import com.example.newsproject.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val repository = NewsRepository()

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news: StateFlow<List<Article>> = _news

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private var currentPage = 1

    private var currentQuery = ""

    private var isLoadingMore = false

    init {
        loadMoreNews()
    }

    fun loadMoreNews() {

        if (isLoadingMore) return

        isLoadingMore = true
        _isLoading.value = true

        viewModelScope.launch {

            try {

                val newArticles = if (currentQuery.isBlank()) {

                    repository.loadNews(currentPage)

                } else {

                    repository.searchNews(
                        currentQuery,
                        currentPage
                    )
                }

                _news.value += newArticles

                currentPage++

            } catch (e: Exception) {
                e.printStackTrace()
            }
            isLoadingMore = false
            _isLoading.value = false        }
    }

    fun searchNews(query: String) {

        currentQuery = query

        currentPage = 1

        _news.value = emptyList()

        loadMoreNews()
    }

}