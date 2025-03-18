package com.training.bookstore.controllers

import com.training.bookstore.domain.AuthorUpdateRequest
import com.training.bookstore.domain.dto.AuthorDto
import com.training.bookstore.domain.dto.AuthorUpdateRequestDto
import com.training.bookstore.services.AuthorService
import com.training.bookstore.toAuthorDto
import com.training.bookstore.toAuthorEntity
import com.training.bookstore.toAuthorUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/v1/authors"])
class AuthorsController(private val authorService: AuthorService) {


    @PostMapping
    fun createAuthor(@RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        return try {
            val createdAuthor = authorService.create(authorDto.toAuthorEntity()).toAuthorDto();
            ResponseEntity(createdAuthor, HttpStatus.CREATED);
        } catch (ex: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
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

    @PutMapping(path = ["/{id}"])
    fun fullUpdate(@PathVariable("id") id: Long, @RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        return try {
            val result = authorService.fullUpdate(id, authorDto.toAuthorEntity())
            ResponseEntity(result.toAuthorDto(), HttpStatus.OK);
        } catch (ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping(path = ["/{id}"])
    fun partialUpdate(
        @PathVariable("id") id: Long,
        @RequestBody authorUpdateRequest: AuthorUpdateRequestDto
    ): ResponseEntity<AuthorDto> {
        return try {
            val result = authorService.partialUpdate(id, authorUpdateRequest.toAuthorUpdateRequest())
            ResponseEntity(result.toAuthorDto(), HttpStatus.OK);
        } catch (ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteAuthor(
        @PathVariable("id") id: Long
    ): ResponseEntity<Unit> {
        authorService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT);
    }

}