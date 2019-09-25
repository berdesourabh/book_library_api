package com.books.app.controller;

import com.books.app.datamapper.EntityToRestMapper;
import com.books.app.datamapper.RestToEntityMapper;
import com.books.app.dto.BookRestDto;
import com.books.app.model.Book;
import com.books.app.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
@Api(value = "Books APIs")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<BookRestDto>> getAll() {
        List<Book> books = bookService.getAll();
        List<BookRestDto> bookRestDto = books.stream().map(b -> EntityToRestMapper.convertToBook(b)).collect(Collectors.toList());
        return new ResponseEntity<>(bookRestDto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Book> create(@Valid @RequestBody BookRestDto request) {
        Book book = RestToEntityMapper.convertToBook(request);
        Book newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);

    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> update(@RequestBody BookRestDto request, @PathVariable Long bookId) {
        Book book = RestToEntityMapper.convertToBook(request);
        Book updatedBook = bookService.update(bookId, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Book> delete(@PathVariable Long bookId) {
        bookService.delete(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> get(@PathVariable Long bookId) {
        Book book = bookService.get(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
