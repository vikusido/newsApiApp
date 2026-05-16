package com.example.newsproject.data.api

import com.example.newsproject.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v1/latest-news")

    suspend fun getLatestNews(

        @Query("apiKey")
        apiKey: String,

        @Query("language")
        language: String = "ru",

        @Query("page_number")
        page: Int = 1,

        @Query("page_size")
        pageSize: Int = 20

    ): NewsResponse


    @GET("v1/search")

    suspend fun searchNews(

        @Query("apiKey")
        apiKey: String,

        @Query("keywords")
        keywords: String,

        @Query("language")
        language: String = "ru",

        @Query("page_number")
        page: Int = 1,

        @Query("page_size")
        pageSize: Int = 20

    ): NewsResponse
}