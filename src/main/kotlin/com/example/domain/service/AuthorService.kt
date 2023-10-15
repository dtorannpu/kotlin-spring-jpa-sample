package com.example.domain.service

import com.example.domain.entity.Author
import com.example.domain.exception.AuthorNotFoundException
import com.example.domain.repository.AuthorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthorService(val authorRepository: AuthorRepository) {
    @Transactional(readOnly = true)
    fun getAll(): List<Author> {
        return authorRepository.findAll()
    }

    @Transactional
    fun create(name: String) {
        val author = Author(null, name, null)
        authorRepository.save(author)
    }

    @Transactional
    fun delete(authorId: Long) {
        authorRepository.deleteById(authorId)
    }

    @Transactional
    fun updateName(authorId: Long, name: String) {
        val author = authorRepository.findById(authorId).orElseThrow { AuthorNotFoundException() }
        val newAuthor = author.copy(name = name)
        authorRepository.save(newAuthor)
    }
}