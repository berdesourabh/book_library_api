package com.books.app.service;

import com.books.app.exception.BookNotFoundException;
import com.books.app.exception.InvalidBookException;
import com.books.app.exception.ReaderNotFoundException;
import com.books.app.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book create(Book book) throws InvalidBookException;

    Book update(Long id, Book book) throws BookNotFoundException;

    void delete(Long id) throws BookNotFoundException;

    Book get(Long id) throws BookNotFoundException;

    Book getByReader(Long readerId) throws ReaderNotFoundException;
}
