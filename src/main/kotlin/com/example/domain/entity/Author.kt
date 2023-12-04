package com.example.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.NamedAttributeNode
import jakarta.persistence.NamedEntityGraph
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.io.Serializable

/**
 * 著者
 *
 * @property id ID
 * @property name 著者名
 * @property books 本リスト
 */
@Entity
@Table(name = "authors")
@NamedEntityGraph(
    name = "Author.detail",
    attributeNodes = [NamedAttributeNode("books")],
)
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(length = 100, nullable = false)
    val name: String,
    @OneToMany(mappedBy = "author")
    val books: MutableList<Book>?,
) : Serializable {
    /**
     * コンストラクタ
     *
     * @param name 著者名
     */
    constructor(name: String) : this(null, name, null)
}
