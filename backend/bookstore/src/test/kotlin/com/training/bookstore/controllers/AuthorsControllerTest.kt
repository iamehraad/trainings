package com.training.bookstore.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.training.bookstore.domain.dto.AuthorUpdateRequestDto
import com.training.bookstore.domain.entities.AuthorEntity
import com.training.bookstore.services.AuthorService
import com.training.bookstore.testAuthorDtoA
import com.training.bookstore.testAuthorEntityA
import com.training.bookstore.testAuthorUpdateRequestDtoA
import io.mockk.every
import io.mockk.verify
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*


@SpringBootTest
@AutoConfigureMockMvc
class AuthorsControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    @MockkBean val authorService: AuthorService
) {

    private val AUTHORS_BASE_URL = "/v1/authors"
    val objectMapper = ObjectMapper()

    @BeforeEach
    fun beforeEach() {
        every {
            authorService.create(any())
        } answers {
            firstArg()
        }
    }

    @Test
    fun `test that create Author saves the author`() {
        mockMvc.post(AUTHORS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorDtoA()
            )
        }

        val expected = AuthorEntity(
            id = null,
            name = "John doe",
            age = 30,
            description = "Lorem ipsum",
            image = "image.jpg"
        )
        verify { authorService.create(expected) }
    }

    @Test
    fun `test that create author returns a HTTP 201 status on a successful create`() {
        mockMvc.post(AUTHORS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorDtoA()
            )
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `test that create author returns a HTTP 400 when illegal exception is thrown`() {
        every {
            authorService.create(any())
        } throws IllegalArgumentException()

        mockMvc.post(AUTHORS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorDtoA()
            )
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `test that list returns empty list when there is no data saved in db`() {

        every {
            authorService.list()
        } answers {
            emptyList()
        }

        mockMvc.get(AUTHORS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { json("[]") }
        }
    }

    @Test
    fun `test that list returns Authors from db`() {

        every {
            authorService.list()
        } answers {
            listOf(testAuthorEntityA(1))
        }

        mockMvc.get(AUTHORS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { jsonPath("$[0].id", equalTo(1)) }
            content { jsonPath("$[0].name", equalTo("John doe")) }
            content { jsonPath("$[0].age", equalTo(30)) }
        }
    }

    @Test
    fun `test that get returns 404 when author not found`() {

        every {
            authorService.get(any())
        } answers {
            null
        }

        mockMvc.get("$AUTHORS_BASE_URL/1234") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNotFound() }
        }
    }

    @Test
    fun `test that get returns 200 and author when author found`() {

        every {
            authorService.get(any())
        } answers {
            testAuthorEntityA(1)
        }

        mockMvc.get("$AUTHORS_BASE_URL/1") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { jsonPath("$.id", equalTo(1)) }
            content { jsonPath("$.name", equalTo("John doe")) }
        }
    }

    @Test
    fun `test that full update Author returns 200 and updated Author on successful call`() {
        every {
            authorService.fullUpdate(any(), any())
        } answers {
            secondArg()
        }
        mockMvc.put("$AUTHORS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(testAuthorDtoA(999))
        }.andExpect {
            status { isOk() }
            content { jsonPath("$.id", equalTo(999)) }
            content { jsonPath("$.name", equalTo("John doe")) }
        }
    }

    @Test
    fun `test that full update Author returns 400 on illegal exception`() {
        every {
            authorService.fullUpdate(any(), any())
        } throws (IllegalStateException())

        mockMvc.put("$AUTHORS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(testAuthorDtoA(999))
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `test that partial update Author returns 400 on illegal state exception`() {
        every {
            authorService.partialUpdate(any(), any())
        } throws (IllegalStateException())

        mockMvc.patch("$AUTHORS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorUpdateRequestDtoA()
            )
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `test that partial update Author returns 200 and updated author`() {
        every {
            authorService.partialUpdate(any(), any())
        } answers {
            testAuthorEntityA(999)
        }

        mockMvc.patch("$AUTHORS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorUpdateRequestDtoA()
            )
        }.andExpect {
            status { isOk() }
            content { jsonPath("$.name", equalTo("John doe")) }
        }
    }

    @Test
    fun `test that delete author returns 204 successfully deleted`() {
        every {
            authorService.delete(any())
        } answers {}

        mockMvc.delete("$AUTHORS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNoContent() }
        }
    }


}