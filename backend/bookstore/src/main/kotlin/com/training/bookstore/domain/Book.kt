package com.training.bookstore.domain

data class Book (
    val isbn: String,
    val title: String,
    val description: String,
    val image: String,
    val author: Author,
)