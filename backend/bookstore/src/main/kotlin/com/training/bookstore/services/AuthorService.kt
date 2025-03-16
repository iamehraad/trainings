package com.training.bookstore.services

import com.training.bookstore.domain.entities.AuthorEntity

interface AuthorService {

    fun save(authorEntity: AuthorEntity): AuthorEntity

}