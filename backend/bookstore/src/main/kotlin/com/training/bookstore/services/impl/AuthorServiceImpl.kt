package com.training.bookstore.services.impl

import com.training.bookstore.domain.entities.AuthorEntity
import com.training.bookstore.repositories.AuthorRepository
import com.training.bookstore.services.AuthorService
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(private val authorRepository: AuthorRepository) : AuthorService {

    override fun save(authorEntity: AuthorEntity): AuthorEntity {
        return authorRepository.save(authorEntity)
    }

}