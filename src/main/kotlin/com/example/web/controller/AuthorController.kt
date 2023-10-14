package com.example.web.controller

import com.example.domain.service.AuthorService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("authors")
class AuthorController(val authorService: AuthorService) {
    /**
     * 全件取得
     *
     * @return 本一覧
     */
    @GetMapping
    fun get(): List<AuthorResponse> {
        return authorService.getAll()
            .map { AuthorResponse(it.id!!, it.name, it.books?.map { b -> BookDto(b.id!!, b.title) }) }
    }

    /**
     * 本作成
     */
    @PostMapping
    fun post(@Validated @RequestBody request: AuthorCreateRequest) {
        authorService.create(request.name)
    }
}