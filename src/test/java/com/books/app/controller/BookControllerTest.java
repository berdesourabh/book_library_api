package com.books.app.controller;

import com.books.app.datamapper.EntityToRestMapper;
import com.books.app.datamapper.RestToEntityMapper;
import com.books.app.domain.Book;
import com.books.app.model.BookDto;
import com.books.app.service.BookService;
import com.books.app.validator.BookValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @MockBean
    BookValidator bookValidator;

    @MockBean
    RestToEntityMapper restToEntityMapper;

    @MockBean
    EntityToRestMapper entityToRestMapper;

    List<Book> booksData;

    @Before
    public void setUp() {
        setupData();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/books/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        Book newBook = booksData.get(0);
        Mockito.when(bookService.create(Mockito.any(Book.class))).thenReturn(newBook);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books/")
                        .content(asJsonString(new BookDto("Test Name", "Test Author")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        Book updatedBook = booksData.get(0);
        Mockito.when(bookService.update(Mockito.anyLong(), Mockito.any(Book.class))).thenReturn(updatedBook);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/books/{bookId}", 1l)
                .content(asJsonString(new BookDto("Test Name", "Test Author")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/books/{bookId}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGet() throws Exception {
        Book updatedBook = booksData.get(0);
        Mockito.when(bookService.get(Mockito.anyLong())).thenReturn(updatedBook);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/{bookId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setupData() {
        booksData = Arrays.asList(
                new Book(1l, "Test book1", "Test author1"),
                new Book(2l, "Test book2", "Test author2"),
                new Book(3l, "Test book3", "Test author3")
        );
    }

}
