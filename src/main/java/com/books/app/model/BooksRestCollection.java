package com.books.app.model;

import java.util.List;

public class BooksRestCollection {

    List<BookDto> books;

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
