package com.example.domain.service

import com.example.domain.entity.Author
import com.example.domain.exception.AuthorNotFoundException
import com.example.domain.repository.AuthorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 著者サービス
 */
@Service
class AuthorService(val authorRepository: AuthorRepository) {
    /**
     * 全件取得
     *
     * @return 著者リスト
     */
    @Transactional(readOnly = true)
    fun getAll(): List<Author> {
        return authorRepository.findAll()
    }

    /**
     * 作成
     *
     * @param name 著者名
     */
    @Transactional
    fun create(name: String) {
        val author = Author(name)
        authorRepository.save(author)
    }

    /**
     * 削除
     *
     * @param authorId 著者ID
     */
    @Transactional
    fun delete(authorId: Long) {
        authorRepository.deleteById(authorId)
    }

    /**
     * 著者名更新
     *
     * @param authorId 著者ID
     * @param name 著者名
     */
    @Transactional
    fun updateName(
        authorId: Long,
        name: String,
    ) {
        val author = authorRepository.findById(authorId).orElseThrow { AuthorNotFoundException() }
        val newAuthor = author.copy(name = name)
        authorRepository.save(newAuthor)
    }
}
