package com.example.domain.repository

import com.example.domain.entity.Author
import com.example.domain.entity.Book
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookRepositoryTests @Autowired constructor(
    val authorRepository: AuthorRepository,
    val bookRepository: BookRepository
) {
    @Test
    fun test() {
        val author1 = Author(null, "夏目漱石", null)
        val author2 = Author(null, "森鴎外", null)
        authorRepository.saveAll(listOf(author1, author2))
        val book1 = Book(null, "こころ", author1)
        val book2 = Book(null, "吾輩は猫である", author1)
        val book3 = Book(null, "舞姫", author2)
        bookRepository.saveAll(listOf(book1, book2, book3))

        bookRepository.findAll()
    }
}
