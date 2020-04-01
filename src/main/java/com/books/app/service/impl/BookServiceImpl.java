package com.books.app.service.impl;

import com.books.app.domain.Book;
import com.books.app.exception.ApiError;
import com.books.app.exception.ApiException;
import com.books.app.exception.ReasonCode;
import com.books.app.repository.BookRepository;
import com.books.app.repository.ReaderRepository;
import com.books.app.service.BookService;
import com.books.app.util.MessagesUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Book create(Book book) throws ApiException {
        try {
            return bookRepository.save(book);
        } catch (Exception ex) {
            throw new ApiException(new ApiError("BookServiceImpl::create", ReasonCode.CREATION_FAILED_003.getCode(), MessagesUtil.getMessage("creation.failed"), HttpStatus.BAD_REQUEST));
        }
    }

    @Override
    @Transactional
    public Book update(Long id, Book book) throws ApiException {
        try {
            if(ObjectUtils.allNotNull(bookRepository.getOne(id))) {
                book.setId(id);
                return bookRepository.save(book);
            }
            return null;
        } catch (Exception ex) {
            throw new ApiException(new ApiError("BookServiceImpl::update", "001", ex.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws ApiException {
        Book existingBook = bookRepository.getOne(id);
        try {
            bookRepository.delete(existingBook);
        } catch (Exception ex) {
            throw new ApiException(new ApiError("BookServiceImpl::delete", "001", ex.getMessage(), HttpStatus.BAD_REQUEST));

        }
    }

    @Override
    @Transactional
    public Book get(Long id) throws ApiException {
        Book existingBook = bookRepository.getOne(id);
        try {
            return existingBook;
        } catch (Exception ex) {
            throw new ApiException(new ApiError("BookServiceImpl::get", "001", ex.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }
}