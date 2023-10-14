package com.example.web.controller

import com.example.domain.entity.Author
import com.example.domain.entity.Book
import com.example.domain.service.AuthorService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [AuthorController::class])
class AuthorControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var authorService: AuthorService

    @Test
    fun 著者全件取得() {
        `when`(authorService.getAll()).thenReturn(
            listOf(
                Author(
                    1, "夏目漱石", mutableListOf(
                        Book(1, "こころ", Author(1, "夏目漱石", null)),
                        Book(2, "吾輩は猫である", Author(1, "夏目漱石", null))
                    )
                ), Author(
                    2, "森鴎外", mutableListOf(
                        Book(3, "舞姫", Author(2, "森鴎外", null))
                    )
                )
            )
        )
        mvc.get("/authors").andExpect { status { isOk() } }
            .andExpect { content { json("[{'authorId':1,'name':'夏目漱石','books':[{'bookId':1,'title':'こころ'},{'bookId':2,'title':'吾輩は猫である'}]},{'authorId':2,'name':'森鴎外','books':[{'bookId':3,'title':'舞姫'}]}]") } }

        verify(authorService, times(1)).getAll()
    }

    @Test
    fun 著者全件取得本なし() {
        `when`(authorService.getAll()).thenReturn(
            listOf(
                Author(
                    1, "夏目漱石", null
                ), Author(
                    2, "森鴎外", null
                )
            )
        )
        mvc.get("/authors")
            .andExpect { status { isOk() } }
            .andExpect { content { json("[{'authorId':1,'authorName':'夏目漱石','books':null},{'authorId':2,'authorName':'森鴎外','books':null}]") } }
        verify(authorService, times(1)).getAll()
    }

    @Test
    fun 著者登録() {
        val body = AuthorCreateRequest("テスト著者")
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(body)

        mvc.perform(
            MockMvcRequestBuilders.post("/authors").contentType(MediaType.APPLICATION_JSON).content(json)
        ).andExpect(status().isOk)

        verify(authorService, times(1)).create("テスト著者")
    }
}