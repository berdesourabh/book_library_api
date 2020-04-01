package com.books.app.controller;

import com.books.app.datamapper.EntityToRestMapper;
import com.books.app.datamapper.RestToEntityMapper;
import com.books.app.domain.Book;
import com.books.app.exception.ApiException;
import com.books.app.model.BookDto;
import com.books.app.service.BookService;
import com.books.app.validator.BookValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@Api(value = "Books APIs")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private EntityToRestMapper entityToRestMapper;

    @Autowired
    private BookValidator bookValidator;

    @ApiOperation(tags = "Books", value = "Get all books ", notes = "This API will be used to get list of available books")
    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAll() {
        logger.info("Fetching all available books");
        List<Book> books = bookService.getAll();
        List<BookDto> bookDto = books.stream().map(EntityToRestMapper::convertToBookRestDto).collect(Collectors.toList());
        return new ResponseEntity<>(bookDto, HttpStatus.OK);

    }

    @ApiOperation(tags = "Books", value = "Create Book ", notes = "This API will be used to create new book")
    @PostMapping("/")
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto request) throws ApiException {
        bookValidator.validateBookRequest(request, null);
        Book book = RestToEntityMapper.convertToBook(request);
        Book newBook = bookService.create(book);
        BookDto bookDto = EntityToRestMapper.convertToBookRestDto(newBook);
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);

    }

    @ApiOperation(tags = "Books", value = "Update book ", notes = "This API will be used to update book detail")
    @PutMapping("/{bookId}")
    public ResponseEntity<BookDto> update(@RequestBody BookDto request, @PathVariable Long bookId) throws ApiException {
        Book book = RestToEntityMapper.convertToBook(request);
        Book updatedBook = bookService.update(bookId, book);
        BookDto bookDto = EntityToRestMapper.convertToBookRestDto(updatedBook);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @ApiOperation(tags = "Books", value = "Delete Book ", notes = "This API will be used to delete book")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> delete(@PathVariable Long bookId) throws ApiException {
        bookService.delete(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(tags = "Books", value = "Get book ", notes = "This API will be used to get book by Id")
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> get(@PathVariable Long bookId) throws ApiException {
        Book book = bookService.get(bookId);
        BookDto bookDto = EntityToRestMapper.convertToBookRestDto(book);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

}