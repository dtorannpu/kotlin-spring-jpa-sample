package com.example.web.controller.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AuthorNameUpdateRequest(
    @field:NotNull
    val authorId: Long,
    @field:NotBlank
    val authorName: String
)