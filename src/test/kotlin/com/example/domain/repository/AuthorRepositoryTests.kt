package com.example.domain.repository

import com.example.domain.entity.Author
import com.example.domain.entity.Book
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthorRepositoryTests
    @Autowired
    constructor(
        val authorRepository: AuthorRepository,
        val bookRepository: BookRepository,
    ) {
        @Test
        fun test() {
            val author1 = Author("夏目漱石")
            val author2 = Author("森鴎外")
            authorRepository.saveAll(listOf(author1, author2))
            val book1 = Book("こころ", author1)
            val book2 = Book("吾輩は猫である", author1)
            val book3 = Book("舞姫", author2)
            bookRepository.saveAll(listOf(book1, book2, book3))

            authorRepository.findAll()
        }
    }
