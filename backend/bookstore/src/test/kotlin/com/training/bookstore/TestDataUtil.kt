package com.training.bookstore

import com.training.bookstore.domain.dto.AuthorDto
import com.training.bookstore.domain.entities.AuthorEntity

fun testAuthorDtoA(id: Long? = null) = AuthorDto(
    id = id, name = "John doe", age = 30, description = "Lorem ipsum", image = "image.jpg"
)

fun testAuthorEntityA(id: Long? = null) = AuthorEntity(
    id = id, name = "John doe", age = 30, description = "Lorem ipsum", image = "image.jpg"
)

