package com.example.newsproject.data.model

data class NewsResponse(

    val status: String,

    val news: List<Article>
)

data class Article(

    val id: String,

    val title: String,

    val description: String?,

    val url: String,

    val image: String?,

    val published: String
)