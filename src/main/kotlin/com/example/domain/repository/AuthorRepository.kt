package com.example.domain.repository

import com.example.domain.entity.Author
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 著者リポジトリ
 */
@Repository
interface AuthorRepository : JpaRepository<Author, Long> {
    /**
     * 全件取得
     *
     * @return 著者リスト
     */
    @EntityGraph(value = "Author.detail", type = EntityGraph.EntityGraphType.LOAD)
    override fun findAll(): MutableList<Author>
}
