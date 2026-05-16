package com.example.newsproject.data.repository

import com.example.newsproject.data.api.RetrofitInstance

class NewsRepository {

    private val apiKey = "x6ATyCF9tmR6fLSs_4MgBDIVX2jcliECsss3TK1KMxe3lvJJ"
    suspend fun loadNews(page: Int) =

        RetrofitInstance.api
            .getLatestNews(
                apiKey = apiKey,
                page = page
            ).news


    suspend fun searchNews(
        query: String,
        page: Int
    ) =

        RetrofitInstance.api
            .searchNews(
                apiKey = apiKey,
                keywords = query,
                page = page
            ).news
}