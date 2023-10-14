package com.example.domain.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "authors")
@NamedEntityGraph(name = "Author.detail",
    attributeNodes = [NamedAttributeNode("books")])
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(length = 100, nullable = false)
    val name: String,
    @OneToMany(mappedBy = "author")
    val books: MutableList<Book>?
) : Serializable