package com.training.bookstore.services.impl

import com.training.bookstore.domain.entities.AuthorEntity
import com.training.bookstore.repositories.AuthorRepository
import com.training.bookstore.services.AuthorService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(private val authorRepository: AuthorRepository) : AuthorService {

    override fun create(authorEntity: AuthorEntity): AuthorEntity {
        require(null == authorEntity.id)
        return authorRepository.save(authorEntity)
    }

    override fun list(): List<AuthorEntity> {
        return authorRepository.findAll()
    }

    override fun get(id: Long): AuthorEntity? {
        return authorRepository.findByIdOrNull(id)
    }

    // Make it atomic
    @Transactional
    override fun fullUpdate(id: Long, authorEntity: AuthorEntity): AuthorEntity {
       check(authorRepository.existsById(id))
        val normalisedAuthor = authorEntity.copy(id=id)
        return authorRepository.save(normalisedAuthor)
    }

}