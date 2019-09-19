package com.books.app.controller;

import com.books.app.model.Book;
import com.books.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = bookService.getAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Book> create(@RequestBody Book request) {
        Book newBook = bookService.create(request);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);

    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> update(@RequestBody Book request, @PathVariable Long bookId) {
        Book book = bookService.update(bookId, request);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Book> delete(@PathVariable Long bookId) {
        Book book = bookService.delete(bookId);
        return new ResponseEntity<>(book, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> get(@PathVariable Long bookId) {
        Book book = bookService.get(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

//    @GetMapping("/{reader_id}")
//    public ResponseEntity<Book> getByReader(@PathVariable Long readerId) {
//        return null;
//    }
}
