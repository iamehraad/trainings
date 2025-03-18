package com.training.bookstore

import com.training.bookstore.domain.AuthorUpdateRequest
import com.training.bookstore.domain.dto.AuthorDto
import com.training.bookstore.domain.dto.AuthorUpdateRequestDto
import com.training.bookstore.domain.entities.AuthorEntity

fun AuthorEntity.toAuthorDto() = AuthorDto(
    id = this.id,
    name = this.name,
    image = this.image,
    description = this.description,
    age = this.age,
)

fun AuthorDto.toAuthorEntity() = AuthorEntity(
    id = this.id,
    name = this.name,
    image = this.image,
    description = this.description,
    age = this.age,
)

fun AuthorUpdateRequestDto.toAuthorUpdateRequest() = AuthorUpdateRequest(
    id = this.id,
    name = this.name,
    image = this.image,
    description = this.description,
    age = this.age,
)