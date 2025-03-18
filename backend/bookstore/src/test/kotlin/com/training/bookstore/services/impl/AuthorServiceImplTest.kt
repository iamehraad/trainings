package com.training.bookstore.services.impl

import com.training.bookstore.repositories.AuthorRepository
import com.training.bookstore.testAuthorEntityA
import com.training.bookstore.testAuthorEntityB
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
    fun `test that list returns empty list when there is no author in db`() {
        val result = underTest.list();
        assertThat(result).isEmpty()
    }

    @Test
    fun `test that save persists the Author in database`() {
        val savedAuthor = underTest.create(testAuthorEntityA())
        assertThat(savedAuthor.id).isNotNull()

        val recalledAuthor = authorRepository.findByIdOrNull(savedAuthor.id!!)
        assertThat(recalledAuthor).isNotNull()
        assertThat(recalledAuthor!!).isEqualTo(
            testAuthorEntityA(id = savedAuthor.id)
        )
    }

    @Test
    fun `test that an author with an ID throws an IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> {
            val existingAuthor = testAuthorEntityA(id = 999)
            underTest.create(existingAuthor)
        }
    }


    @Test
    fun `test that list returns authors when there is authors in db`() {
        val savedAuthor = authorRepository.save(testAuthorEntityA())
        val expected = listOf(savedAuthor);
        val result = underTest.list();
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `test that get returns null when author is not in db`() {
        val result = underTest.get(999);
        assertThat(result).isNull()
    }

    @Test
    fun `test that get returns author when author is in db`() {
        val savedAuthor = authorRepository.save(testAuthorEntityA())
        val result = underTest.get(savedAuthor.id!!);
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(savedAuthor)
    }

    @Test
    fun `Test that full update, successfully updates author in DB`() {
        val exisingAuthor = authorRepository.save(testAuthorEntityA())
        val exisingAuthorId = exisingAuthor.id!!
        val updatedAuthor = testAuthorEntityB(exisingAuthorId)
        val result = underTest.fullUpdate(exisingAuthorId, updatedAuthor)
        assertThat(result).isEqualTo(updatedAuthor)

        val retrievedAuthor = authorRepository.findByIdOrNull(exisingAuthorId)
        assertThat(retrievedAuthor).isNotNull()
        assertThat(retrievedAuthor).isEqualTo(updatedAuthor)
    }

    @Test
    fun `test that full update author throws IllegalStateException`() {
        assertThrows<IllegalStateException> {
            val nonExistingAuthorId = 1234L
            val updatedAuthor = testAuthorEntityB(nonExistingAuthorId)
            underTest.fullUpdate(nonExistingAuthorId, updatedAuthor)
        }
    }

}