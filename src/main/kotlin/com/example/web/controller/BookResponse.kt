package com.example.web.controller

data class BookResponse(
    val bookId: Long,
    val title: String,
    val authorId: Long,
    val author: String
)