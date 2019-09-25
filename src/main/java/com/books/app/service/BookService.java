package com.books.app.service;

import com.books.app.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book create(Book book);

    Book update(Long id, Book book);

    void delete(Long id);

    Book get(Long id);
}
