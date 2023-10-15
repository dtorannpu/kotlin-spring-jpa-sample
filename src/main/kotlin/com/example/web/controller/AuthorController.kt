package com.example.web.controller

import com.example.domain.service.AuthorService
import com.example.web.controller.request.AuthorCreateRequest
import com.example.web.controller.request.AuthorNameUpdateRequest
import com.example.web.controller.response.AuthorResponse
import com.example.web.controller.response.BookDto
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 著者コントローラー
 */
@RestController
@RequestMapping("authors")
class AuthorController(val authorService: AuthorService) {
    /**
     * 全件取得
     *
     * @return 著者一覧
     */
    @GetMapping
    fun get(): List<AuthorResponse> {
        return authorService.getAll()
            .map { AuthorResponse(it.id!!, it.name, it.books?.map { b -> BookDto(b.id!!, b.title) }) }
    }

    /**
     * 著者作成
     */
    @PostMapping
    fun post(
        @Validated @RequestBody
        request: AuthorCreateRequest
    ) {
        authorService.create(request.name)
    }

    /**
     * 著者削除
     *
     * @param authorId 著者ID
     */
    @DeleteMapping("{authorId}")
    fun delete(@PathVariable("authorId") authorId: Long) {
        authorService.delete(authorId)
    }

    /**
     * 著者名更新
     *
     * @param authorNameUpdateRequest リクエスト
     */
    @PatchMapping
    fun update(
        @Validated @RequestBody
        authorNameUpdateRequest: AuthorNameUpdateRequest
    ) {
        authorService.updateName(authorNameUpdateRequest.authorId, authorNameUpdateRequest.authorName)
    }
}
