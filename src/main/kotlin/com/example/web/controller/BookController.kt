package com.example.web.controller

import com.example.domain.service.BookService
import com.example.web.controller.request.BookCreateRequest
import com.example.web.controller.response.BookResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 本コントローラー
 */
@RestController
@RequestMapping("books")
class BookController(val bookService: BookService) {
    /**
     * 全件取得
     *
     * @return 本一覧
     */
    @GetMapping
    fun get(): List<BookResponse> {
        return bookService.getAll().map { BookResponse(it.id!!, it.title, it.author.id!!, it.author.name) }
    }

    /**
     * 本作成
     *
     * @param request リクエスト
     */
    @PostMapping
    fun post(@Validated @RequestBody request: BookCreateRequest) {
        bookService.create(request.title, request.authorId)
    }

    /**
     * 本削除
     *
     * @param bookId 本ID
     */
    @DeleteMapping("{bookId}")
    fun delete(@PathVariable("bookId") bookId: Long) {
        bookService.delete(bookId)
    }
}