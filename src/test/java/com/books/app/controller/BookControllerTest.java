package com.books.app.controller;

import com.books.app.model.Book;
import com.books.app.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
//Alias for SpringJunit4Runner which provides functionality for Spring TestContext framework for std Junit.
@WebMvcTest(BookController.class)
//disables auto configuration irrelevant to  mvc tes. Also auto-configure MockMvc instance.
// Only BookController will be initialized.
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/book/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/book/")
                        .content(asJsonString(new Book(null, "Test Name", "Test Author")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/book/{bookId}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
