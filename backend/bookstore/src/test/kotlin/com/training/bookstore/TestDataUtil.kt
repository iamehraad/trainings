package com.training.bookstore

import com.training.bookstore.domain.AuthorUpdateRequest
import com.training.bookstore.domain.dto.AuthorDto
import com.training.bookstore.domain.dto.AuthorUpdateRequestDto
import com.training.bookstore.domain.entities.AuthorEntity

fun testAuthorDtoA(id: Long? = null) = AuthorDto(
    id = id, name = "John doe", age = 30, description = "Lorem ipsum", image = "image.jpg"
)

fun testAuthorEntityA(id: Long? = null) = AuthorEntity(
    id = id, name = "John doe", age = 30, description = "Lorem ipsum", image = "image.jpg"
)

fun testAuthorEntityB(id: Long? = null) = AuthorEntity(
    id = id, name = "Don doe", age = 30, description = "Lorem ipsum", image = "image.jpg"
)

fun testAuthorUpdateRequestDtoA(id: Long? = null) = AuthorUpdateRequestDto(
    id = id,
    name = "John doe",
    age = 30,
    image = "image.jpg",
    description = "something"
)

fun testAuthorUpdateRequestA(id: Long? = null) = AuthorUpdateRequest(
    id = id,
    name = "John doe",
    age = 30,
    image = "image.jpg",
    description = "something"
)
