package com.example.domain.repository

import com.example.domain.entity.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Long> {
}