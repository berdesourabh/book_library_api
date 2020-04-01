package com.books.app.datamapper;

import com.books.app.model.BookDto;
import com.books.app.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class EntityToRestMapper {

    private EntityToRestMapper() {
    }

    public static BookDto convertToBookRestDto(final Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());

        return bookDto;
    }
}
