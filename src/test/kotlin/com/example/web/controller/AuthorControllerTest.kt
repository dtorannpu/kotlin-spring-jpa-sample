package com.example.web.controller

import com.example.domain.entity.Author
import com.example.domain.entity.Book
import com.example.domain.service.AuthorService
import com.example.web.controller.request.AuthorCreateRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [AuthorController::class])
class AuthorControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockitoBean
    private lateinit var authorService: AuthorService

    @Test
    fun testAuthorGetAll() {
        `when`(authorService.getAll()).thenReturn(
            listOf(
                Author(
                    1,
                    "夏目漱石",
                    mutableListOf(
                        Book(1, "こころ", Author(1, "夏目漱石", null)),
                        Book(2, "吾輩は猫である", Author(1, "夏目漱石", null)),
                    ),
                ),
                Author(
                    2,
                    "森鴎外",
                    mutableListOf(
                        Book(3, "舞姫", Author(2, "森鴎外", null)),
                    ),
                ),
            ),
        )
        mvc
            .get("/authors")
            .andExpect { status { isOk() } }
            .andExpect {
                content {
                    json(
                        """
                        [
                          {
                            "authorId": 1,
                            "authorName": "夏目漱石",
                            "books": [
                              {
                                "bookId": 1,
                                "title": "こころ"
                              },
                              {
                                "bookId": 2,
                                "title": "吾輩は猫である"
                              }
                            ]
                          },
                          {
                            "authorId": 2,
                            "authorName": "森鴎外",
                            "books": [
                              {
                                "bookId": 3,
                                "title": "舞姫"
                              }
                            ]
                          }
                        ]
                        """.trimIndent(),
                    )
                }
            }

        verify(authorService, times(1)).getAll()
    }

    @Test
    fun testGetAllAuthor() {
        `when`(authorService.getAll()).thenReturn(
            listOf(
                Author(
                    1,
                    "夏目漱石",
                    null,
                ),
                Author(
                    2,
                    "森鴎外",
                    null,
                ),
            ),
        )
        mvc
            .get("/authors")
            .andExpect { status { isOk() } }
            .andExpect {
                content {
                    json(
                        "[{'authorId':1,'authorName':'夏目漱石','books':null},{'authorId':2,'authorName':'森鴎外','books':null}]",
                    )
                }
            }
        verify(authorService, times(1)).getAll()
    }

    @Test
    fun testAuthorCreate() {
        val body = AuthorCreateRequest("テスト著者")
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(body)

        mvc
            .perform(
                MockMvcRequestBuilders.post("/authors").contentType(MediaType.APPLICATION_JSON).content(json),
            ).andExpect(status().isOk)

        verify(authorService, times(1)).create("テスト著者")
    }

    @Test
    fun testAuthorDelete() {
        mvc
            .delete("/authors/1")
            .andExpect { status { isOk() } }

        verify(authorService, times(1)).delete(1)
    }
}
