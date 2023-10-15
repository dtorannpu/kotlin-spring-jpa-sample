package com.example.web.controller.response

/**
 * 本レスポンス
 *
 * @property bookId 本ID
 * @property title タイトル
 * @property authorId 著者ID
 * @property authorName 著者名
 */
data class BookResponse(
    val bookId: Long,
    val title: String,
    val authorId: Long,
    val authorName: String
)