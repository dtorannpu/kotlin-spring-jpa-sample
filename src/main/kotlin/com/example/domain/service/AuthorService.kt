package com.example.domain.service

import com.example.domain.entity.Author
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
}