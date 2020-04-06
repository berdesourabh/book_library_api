package com.books.app.datamapper;

import com.books.app.domain.Book;
import com.books.app.model.BookDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EntityToRestMapper {

    public static BookDto convertToBookRestDto(final Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());

        return bookDto;
    }
}
