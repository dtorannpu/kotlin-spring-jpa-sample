package com.example.domain.service

import com.example.domain.entity.Author
import com.example.domain.entity.Book
import com.example.domain.exception.AuthorNotFoundException
import com.example.domain.repository.AuthorRepository
import com.example.domain.repository.BookRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import java.util.*


class BookServiceTest @Autowired constructor() {
    private lateinit var authorRepository: AuthorRepository
    private lateinit var bookRepository: BookRepository
    private lateinit var bookService: BookService

    @BeforeEach
    fun setUp() {
        authorRepository = mock(AuthorRepository::class.java)
        bookRepository = mock(BookRepository::class.java)
        bookService = BookService(bookRepository, authorRepository)
    }

    @Test
    fun getAllメソッドでデータが取得できる() {
        `when`(bookRepository.findAll()).thenReturn(
            mutableListOf(
                Book(1, "タイトル１", Author(1, "作者１", null)),
                Book(2, "タイトル２", Author(1, "作者１", null))
            )
        )

        val books = bookService.getAll()
        assertEquals(2, books.size)
        verify(bookRepository, times(1)).findAll()
    }

    @Test
    fun 書籍作成時に作者が存在しない場合はエラー() {
        assertThrows(AuthorNotFoundException::class.java) { bookService.create("test", 1) }
        verify(authorRepository, times(1)).findById(1)
        verify(bookRepository, never()).save(any())
    }

    @Test
    fun createメソッドで保存できる() {
        val author = Author(1, "著者", null)
        val book = Book(null, "タイトル", author)
        `when`(authorRepository.findById(1)).thenReturn(Optional.of(author))

        bookService.create("タイトル", 1)

        verify(authorRepository, times(1)).findById(1)
        verify(bookRepository, times(1)).save(book)
    }

    @Test
    fun deleteメソッドで削除できる() {
        bookService.delete(1)

        verify(bookRepository, times(1)).deleteById(1)
    }
}