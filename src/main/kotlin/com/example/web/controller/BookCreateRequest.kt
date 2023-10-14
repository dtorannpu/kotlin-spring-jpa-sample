package com.example.web.controller

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * 本作成リクエスト
 *
 * @param title タイトル
 */
data class BookCreateRequest(
    @field:NotNull
    val authorId: Long,
    @field:NotBlank
    val title: String
)