package com.example.domain.service

import com.example.domain.entity.Author
import com.example.domain.repository.AuthorRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.Optional

class AuthorServiceTest {
    private lateinit var authorRepository: AuthorRepository
    private lateinit var authorService: AuthorService

    @BeforeEach
    fun setUp() {
        authorRepository = mock(AuthorRepository::class.java)
        authorService = AuthorService(authorRepository)
    }

    @Test
    fun testGetAll() {
        `when`(authorRepository.findAll()).thenReturn(
            mutableListOf(
                Author(1, "作者１", null),
                Author(2, "作者２", null),
            ),
        )

        val authors = authorService.getAll()
        Assertions.assertEquals(2, authors.size)
        verify(authorRepository, times(1)).findAll()
    }

    @Test
    fun testCreate() {
        val author = Author("著者")

        authorService.create("著者")

        verify(authorRepository, times(1)).save(author)
    }

    @Test
    fun testDelete() {
        authorService.delete(1)

        verify(authorRepository, times(1)).deleteById(1)
    }

    @Test
    fun testUpdate() {
        `when`(authorRepository.findById(1)).thenReturn(Optional.of(Author(1, "著者", null)))

        authorService.updateName(1, "著者変更")

        verify(authorRepository, times(1)).save(Author(1, "著者変更", null))
    }
}
