package com.example.domain.service

import com.example.domain.entity.Author
import com.example.domain.repository.AuthorRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AuthorServiceTest {
    private lateinit var authorRepository: AuthorRepository
    private lateinit var authorService: AuthorService

    @BeforeEach
    fun setUp() {
        authorRepository = Mockito.mock(AuthorRepository::class.java)
        authorService = AuthorService(authorRepository)
    }

    @Test
    fun getAllメソッドでデータが取得できる() {
        Mockito.`when`(authorRepository.findAll()).thenReturn(
            mutableListOf(
                Author(1, "作者１", null),
                Author(2, "作者２", null)
            )
        )

        val authors = authorService.getAll()
        Assertions.assertEquals(2, authors.size)
        Mockito.verify(authorRepository, Mockito.times(1)).findAll()
    }

    @Test
    fun createメソッドで保存できる() {
        val author = Author(null, "著者", null)

        authorService.create("著者")

        Mockito.verify(authorRepository, Mockito.times(1)).save(author)
    }
}