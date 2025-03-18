package com.training.bookstore.services.impl

import com.training.bookstore.domain.AuthorUpdateRequest
import com.training.bookstore.domain.entities.AuthorEntity
import com.training.bookstore.repositories.AuthorRepository
import com.training.bookstore.testAuthorEntityA
import com.training.bookstore.testAuthorEntityB
import com.training.bookstore.testAuthorUpdateRequestA
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class AuthorServiceImplTest @Autowired constructor(
    private val underTest: AuthorServiceImpl,
    private val authorRepository: AuthorRepository
) {

    @Test
    fun `test that save persists the Author in the database`() {
        val savedAuthor = underTest.create(testAuthorEntityA())
        assertThat(savedAuthor.id).isNotNull()

        val recalledAuthor = authorRepository.findByIdOrNull(savedAuthor.id!!)
        assertThat(recalledAuthor).isNotNull()
        assertThat(recalledAuthor!!).isEqualTo(
            testAuthorEntityA(id = savedAuthor.id)
        )
    }

    @Test
    fun `test that an Author with an ID throws an IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> {
            val existingAuthor = testAuthorEntityA(id = 999)
            underTest.create(existingAuthor)
        }
    }

    @Test
    fun `test that list returns empty list when no authors in the database`() {
        val result = underTest.list()
        assertThat(result).isEmpty()
    }

    @Test
    fun `test that list returns authors when authors present in the database`() {
        val savedAuthor = authorRepository.save(testAuthorEntityA())
        val expected = listOf(savedAuthor)
        val result = underTest.list()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `test that get returns null when author not present in the database`() {
        val result = underTest.get(999)
        assertThat(result).isNull()
    }

    @Test
    fun `test that get returns author when author is present in the database`() {
        val savedAuthor = authorRepository.save(testAuthorEntityA())
        val result = underTest.get(savedAuthor.id!!)
        assertThat(result).isEqualTo(savedAuthor)
    }

    @Test
    fun `test that full update successfully updates the author in the database`() {
        val existingAuthor = authorRepository.save(testAuthorEntityA())
        val existingAuthorId = existingAuthor.id!!
        val updatedAuthor = testAuthorEntityB(id = existingAuthorId)
        val result = underTest.fullUpdate(existingAuthorId, updatedAuthor)
        assertThat(result).isEqualTo(updatedAuthor)

        val retrievedAuthor = authorRepository.findByIdOrNull(existingAuthorId)
        assertThat(retrievedAuthor).isNotNull()
        assertThat(retrievedAuthor).isEqualTo(updatedAuthor)
    }

    @Test
    fun `test that full update Author throws IllegalStateException when Author does not exist in the database`() {
        assertThrows<IllegalStateException> {
            val nonExistingAuthorId = 999L
            val updatedAuthor = testAuthorEntityB(id = nonExistingAuthorId)
            underTest.fullUpdate(nonExistingAuthorId, updatedAuthor)
        }
    }

    @Test
    fun `test that partial update throws IllegalStateException when Author does not exist in the database`() {
        assertThrows<IllegalStateException> {
            val nonExistingAuthorId = 999L
            val updateRequest = testAuthorUpdateRequestA(id = nonExistingAuthorId)
            underTest.partialUpdate(nonExistingAuthorId, updateRequest)
        }
    }

    @Test
    fun `test that delete, deletes an existing author from DB`() {
        val existingAuthor = authorRepository.save(testAuthorEntityA())
        underTest.delete(existingAuthor.id!!)
        assertThat(authorRepository.existsById(existingAuthor.id!!)).isFalse()
    }

    @Test
    fun `test that delete, deletes an non existing author from DB`() {
        val nonExistingId = 999L
        underTest.delete(nonExistingId)
        assertThat(authorRepository.existsById(nonExistingId)).isFalse()
    }



    @Test
    fun `test that partial update author does not update when all values are null`() {
        val existingAuthor = authorRepository.save(testAuthorEntityA())
        val updatedAuthor = underTest.partialUpdate(existingAuthor.id!!, AuthorUpdateRequest())
        assertThat(updatedAuthor).isEqualTo(existingAuthor)
    }

    @Test
    fun `test that partial update author updates author name`() {
        val newName = "New name"
        val existingAuthor = testAuthorEntityA()
        val expectedAuthor = existingAuthor.copy(name = newName)
        val authorUpdateRequest = AuthorUpdateRequest(name = newName)
        assertThatAuthorPartialUpdateIsUpdated(
            existingAuthor = existingAuthor,
            expectedAuthor = expectedAuthor,
            authorUpdateRequest = authorUpdateRequest
        )
    }

    @Test
    fun `test that partial update author updates author age`() {
        val newAge = 90
        val existingAuthor = testAuthorEntityA()
        val expectedAuthor = existingAuthor.copy(age = newAge)
        val authorUpdateRequest = AuthorUpdateRequest(age = newAge)
        assertThatAuthorPartialUpdateIsUpdated(
            existingAuthor = existingAuthor,
            expectedAuthor = expectedAuthor,
            authorUpdateRequest = authorUpdateRequest
        )
    }

    private fun assertThatAuthorPartialUpdateIsUpdated(
        existingAuthor: AuthorEntity,
        expectedAuthor: AuthorEntity,
        authorUpdateRequest: AuthorUpdateRequest,
    ) {
        val savedExistingAuthor = authorRepository.save(existingAuthor)
        val updatedAuthor = underTest.partialUpdate(existingAuthor.id!!, authorUpdateRequest)
        val expected = expectedAuthor.copy(id = savedExistingAuthor.id!!)
        assertThat(updatedAuthor).isEqualTo(expected)

        val retrievedAuthor = authorRepository.findByIdOrNull(existingAuthor.id!!)
        assertThat(retrievedAuthor).isNotNull()
        assertThat(retrievedAuthor).isEqualTo(expected)
    }

}