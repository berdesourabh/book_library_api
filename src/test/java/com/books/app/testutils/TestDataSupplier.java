package com.books.app.testutils;

import com.books.app.domain.Book;
import com.books.app.domain.Reader;
import com.books.app.model.ReaderDto;

import java.util.Arrays;
import java.util.List;

public class TestDataSupplier {

    public static List<Book> setUpBookList() {
        return Arrays.asList(
                new Book(1l, "Test book1", "Test author1"),
                new Book(2l, "Test book2", "Test author2"),
                new Book(3l, "Test book3", "Test author3")
        );
    }

    public static ReaderDto setupReaderDto() {
        return new ReaderDto("Test Reader Name1", (short) 21);
    }

    public static Book setupBookData() {
        return new Book(1L, "Test Book Name", "Test Author");
    }

    public static List<Reader> setupReaderList() {
        return Arrays.asList(
                new Reader(1l, "Test Reader Name1", (short) 21),
                new Reader(2l, "Test Reader Name1", (short) 16),
                new Reader(3l, "Test Reader Name1", (short) 33)
        );
    }
}
