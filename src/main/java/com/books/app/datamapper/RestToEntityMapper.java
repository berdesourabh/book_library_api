package com.books.app.datamapper;

import com.books.app.domain.Book;
import com.books.app.domain.Reader;
import com.books.app.model.BookDto;
import com.books.app.model.ReaderDto;
import org.springframework.stereotype.Component;


@Component
public class RestToEntityMapper {

    private RestToEntityMapper() {
    }

    public static Book convertToBook(final BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());

        return book;
    }

    public static Reader convertToReader(final ReaderDto readerDto) {
        Reader reader = new Reader();
        reader.setName(readerDto.getName());
        reader.setAge(readerDto.getAge());
        return reader;
    }

}
