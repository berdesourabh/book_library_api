package com.books.app.service.impl;

import com.books.app.datamapper.RestToEntityMapper;
import com.books.app.exception.ApiException;
import com.books.app.domain.Book;
import com.books.app.domain.Reader;
import com.books.app.model.ReaderDto;
import com.books.app.repository.ReaderRepository;
import com.books.app.service.BookService;
import com.books.app.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookService bookService;

    @Override
    public List<Reader> getAll() {
        return readerRepository.findAll();
    }

    @Override
    public Reader create(ReaderDto readerDto) {
        Reader reader = RestToEntityMapper.convertToReader(readerDto);
        return readerRepository.saveAndFlush(reader);
    }

    @Override
    public Reader assignBooks(List<Long> bookIds, Long readerId) {
        Set<Book> requestedBooks = new HashSet<>();
        Reader currentReader = readerRepository.getOne(readerId);
        bookIds.forEach(bookId -> {
            Book book = null;
            try {
                book = bookService.get(bookId);
            } catch (ApiException e) {
                e.printStackTrace();
            }
            book.setReader(currentReader);
            requestedBooks.add(book);
        });
        currentReader.setBooks(requestedBooks);
        readerRepository.saveAndFlush(currentReader);
        return currentReader;
    }
}
