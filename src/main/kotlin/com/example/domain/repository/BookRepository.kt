package com.example.domain.repository

import com.example.domain.entity.Book
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {
    @EntityGraph(value = "Book.detail", type = EntityGraph.EntityGraphType.LOAD)
    override fun findAll(): MutableList<Book>
}