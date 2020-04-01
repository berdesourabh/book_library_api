package com.books.app.controller;

import com.books.app.model.AssignBooksRequest;
import com.books.app.domain.Reader;
import com.books.app.model.ReaderDto;
import com.books.app.service.ReaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/readers")
@Api(value = "Reader APIs")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @ApiOperation(tags = "Readers", value = "Get all Readers ", notes = "This API will be used to get list of available reader")
    @GetMapping("/")
    public ResponseEntity<List<Reader>> getAll() {
        List<Reader> readers = readerService.getAll();
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @ApiOperation(tags = "Readers", value = "Create new reader ", notes = "This API will be used to create new reader")
    @PostMapping("/")
    public ResponseEntity<Reader> create(@Valid @RequestBody ReaderDto request) {
        Reader newReader = readerService.create(request);
        return new ResponseEntity<>(newReader, HttpStatus.CREATED);
    }

    @ApiOperation(tags = "Readers", value = "Assign books to reader", notes = "This API will be used to assign books to reader")
    @PostMapping("/assign")
    public ResponseEntity<Reader> assignBooks(@RequestParam("readerId") Long readerId, @RequestBody AssignBooksRequest booksRequest) {

        Reader reader = readerService.assignBooks(booksRequest.getBookIds(), readerId);

        return new ResponseEntity<>(reader, HttpStatus.OK);
    }
}
