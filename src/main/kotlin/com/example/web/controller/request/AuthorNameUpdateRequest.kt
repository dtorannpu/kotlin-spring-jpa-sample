package com.example.web.controller.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * 著者名更新リクエスト
 *
 * @param authorId 著者ID
 * @param authorName 著者名
 */
data class AuthorNameUpdateRequest(
    @field:NotNull
    val authorId: Long,
    @field:NotBlank
    val authorName: String
)