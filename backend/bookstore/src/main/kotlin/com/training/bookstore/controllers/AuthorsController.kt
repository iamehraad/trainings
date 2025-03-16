package com.training.bookstore.controllers

import com.training.bookstore.domain.dto.AuthorDto
import com.training.bookstore.services.AuthorService
import com.training.bookstore.toAuthorDto
import com.training.bookstore.toAuthorEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorsController(private val authorService: AuthorService) {


    @PostMapping(path = ["/v1/authors"])
    fun createAuthor(@RequestBody authorDto: AuthorDto): AuthorDto {
        return authorService.save(authorDto.toAuthorEntity()).toAuthorDto()
    }
}