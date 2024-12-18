package com.example.domain.service

import com.example.domain.entity.Book
import com.example.domain.exception.AuthorNotFoundException
import com.example.domain.repository.AuthorRepository
import com.example.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 本サービス
 */
@Service
class BookService(
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository,
) {
    /**
     * 全件取得
     *
     * @return 本一覧
     */
    @Transactional(readOnly = true)
    fun getAll(): List<Book> = bookRepository.findAll()

    /**
     * 本作成
     *
     * @param title タイトル
     * @param authorId 著者ID
     */
    @Transactional
    fun create(
        title: String,
        authorId: Long,
    ) {
        val author = authorRepository.findById(authorId).orElseThrow { AuthorNotFoundException() }
        val book = Book(null, title, author)
        bookRepository.save(book)
    }

    /**
     * 本削除
     *
     * @param bookId 本ID
     */
    @Transactional
    fun delete(bookId: Long) {
        bookRepository.deleteById(bookId)
    }
}
