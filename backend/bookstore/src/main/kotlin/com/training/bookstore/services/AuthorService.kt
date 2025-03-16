package com.training.bookstore.services

import com.training.bookstore.domain.entities.AuthorEntity

interface AuthorService {

    fun save(authorEntity: AuthorEntity): AuthorEntity

    fun list():  List<AuthorEntity>

    fun get(id: Long): AuthorEntity?

}