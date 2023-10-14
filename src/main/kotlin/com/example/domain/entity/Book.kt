package com.example.domain.entity

import jakarta.persistence.*
import java.io.Serializable

/**
 * 本
 * @property id ID
 * @property title タイトル
 */
@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(length = 100, nullable = false)
    val title: String,
    @ManyToOne
    val author: Author
) : Serializable
