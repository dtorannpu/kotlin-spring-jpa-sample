package com.example.web.controller

import jakarta.validation.constraints.NotBlank

data class AuthorCreateRequest(
    @field:NotBlank
    val name: String
)