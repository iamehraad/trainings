package com.training.bookstore.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "authors")
data class Author(
    @Id
    @Column(name = "id")
    val id: Long?,

    @Column(name = "name")
    val name: String,

    @Column(name = "age")
    val age: Int,

    @Column(name = "description")
    val description: String,

    @Column(name = "image")
    val image: String,
)