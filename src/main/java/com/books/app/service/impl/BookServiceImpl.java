package com.books.app.service.impl;

import com.books.app.exception.BookNotFoundException;
import com.books.app.exception.InvalidBookException;
import com.books.app.model.Book;
import com.books.app.repository.BookRepository;
import com.books.app.repository.ReaderRepository;
import com.books.app.service.BookService;
import com.books.app.util.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book create(Book book) {

        if (isBookValid(book)) {
            return bookRepository.save(book);
        } else throw new InvalidBookException(MessagesUtil.getMessage("book.not.found.001"));
    }

    @Override
    @Transactional
    public Book update(Long id, Book book) {
        Book existingBook = bookRepository.getOne(id);
        if (isBookValid(existingBook)) {
            book.setId(id);
            return bookRepository.save(book);
        } else throw new BookNotFoundException(MessagesUtil.getMessage("book.not.found.001"));

    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book existingBook = bookRepository.getOne(id);
        if (isBookValid(existingBook)) {
            bookRepository.delete(existingBook);
        } else throw new BookNotFoundException(MessagesUtil.getMessage("book.not.found.001"));

    }

    @Override
    @Transactional
    public Book get(Long id) {
        Book existingBook = bookRepository.getOne(id);
        if (isBookValid(existingBook)) {
            return existingBook;
        } else throw new BookNotFoundException(MessagesUtil.getMessage("book.not.found.001"));
    }

    private boolean isBookValid(Book book) {
        return book != null && !book.getName().isEmpty();
    }
}
