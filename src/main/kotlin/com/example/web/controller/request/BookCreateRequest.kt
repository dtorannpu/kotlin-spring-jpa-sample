package com.example.web.controller.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * 本作成リクエスト
 *
 * @param authorId 著者ID
 * @param title タイトル
 */
data class BookCreateRequest(
    @field:NotNull
    val authorId: Long,
    @field:NotBlank
    val title: String
)