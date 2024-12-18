package com.example.web.controller

import com.example.domain.entity.Author
import com.example.domain.entity.Book
import com.example.domain.service.BookService
import com.example.web.controller.request.BookCreateRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [BookController::class])
class BookControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockitoBean
    private lateinit var bookService: BookService

    @Test
    fun testAuthorGetAll() {
        `when`(bookService.getAll()).thenReturn(
            listOf(
                Book(1, "テスト１", Author(1, "テスト１著者", null)),
                Book(2, "テスト２", Author(2, "テスト２著者", null)),
            ),
        )
        mvc
            .get("/books")
            .andExpect { status { isOk() } }
            .andExpect {
                content {
                    json(
                        """
                        [
                          {
                            "bookId": 1,
                            "title": "テスト１",
                            "authorId": 1,
                            "authorName": "テスト１著者"
                          },
                          {
                            "bookId": 2,
                            "title": "テスト２",
                            "authorId": 2,
                            "authorName": "テスト２著者"
                          }
                        ]
                        """.trimIndent(),
                    )
                }
            }

        verify(bookService, times(1)).getAll()
    }

    @Test
    fun testCreateBook() {
        val body = BookCreateRequest(1, "テスト本")
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(body)

        mvc
            .perform(
                MockMvcRequestBuilders.post("/books").contentType(MediaType.APPLICATION_JSON).content(json),
            ).andExpect(MockMvcResultMatchers.status().isOk)

        verify(bookService, times(1)).create("テスト本", 1)
    }

    @Test
    fun testDeleteBook() {
        mvc
            .delete("/books/1")
            .andExpect { status { isOk() } }

        verify(bookService, times(1)).delete(1)
    }
}
