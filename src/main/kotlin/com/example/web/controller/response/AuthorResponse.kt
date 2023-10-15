package com.example.web.controller.response

/**
 * 著者レスポンス
 *
 * @property authorId 著者ID
 * @property authorName 著者名
 * @property books 本リスト
 */
data class AuthorResponse(val authorId: Long, val authorName: String, val books: List<BookDto>?)
