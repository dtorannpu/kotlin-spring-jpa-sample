package com.example.web.controller.request

import jakarta.validation.constraints.NotBlank

/**
 * 著者作成リクエスト
 *
 * @param name 著者名
 */
data class AuthorCreateRequest(
    @field:NotBlank
    val name: String
)
