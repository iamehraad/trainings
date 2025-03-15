package com.training.bookstore.domain

class Book (
    val isbn: String,
    val title: String,
    val description: String,
    val image: String,
    val author: Author,
)