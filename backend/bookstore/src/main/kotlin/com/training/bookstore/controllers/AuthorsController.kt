package com.training.bookstore.controllers

import com.training.bookstore.domain.dto.AuthorDto
import com.training.bookstore.services.AuthorService
import com.training.bookstore.toAuthorDto
import com.training.bookstore.toAuthorEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/v1/authors"])
class AuthorsController(private val authorService: AuthorService) {


    @PostMapping
    fun createAuthor(@RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        val createdAuthor = authorService.save(authorDto.toAuthorEntity()).toAuthorDto();
        return ResponseEntity(createdAuthor, HttpStatus.CREATED);
    }

    @GetMapping
    fun readManyAuthors(): List<AuthorDto> {
        return authorService.list().map { it.toAuthorDto() };
    }

    @GetMapping(path = ["/{id}"])
    fun readOneAuthor(@PathVariable("id") id: Long): ResponseEntity<AuthorDto> {
        val foundedAuthor = authorService.get(id)?.toAuthorDto();
        foundedAuthor?.let {
            return ResponseEntity(it, HttpStatus.OK)
        } ?: return ResponseEntity(HttpStatus.NOT_FOUND)

    }

}