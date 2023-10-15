package com.example.web.controller.response

data class BookResponse(
    val bookId: Long,
    val title: String,
    val authorId: Long,
    val authorName: String
)