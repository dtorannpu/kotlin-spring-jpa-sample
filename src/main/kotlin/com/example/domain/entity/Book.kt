package com.example.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.NamedAttributeNode
import jakarta.persistence.NamedEntityGraph
import jakarta.persistence.Table
import java.io.Serializable

/**
 * 本
 * @property id ID
 * @property title タイトル
 * @property author 著者
 */
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "Book.detail", attributeNodes = [NamedAttributeNode("author")])
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(length = 100, nullable = false)
    val title: String,
    @ManyToOne
    val author: Author
) : Serializable {
    /**
     * コンストラクタ
     *
     * @param title タイトル
     * @param author 著者
     */
    constructor(title: String, author: Author) : this(null, title, author)
}
