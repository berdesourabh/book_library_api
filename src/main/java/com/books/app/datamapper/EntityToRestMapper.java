package com.books.app.datamapper;

import com.books.app.dto.BookRestDto;
import com.books.app.model.Book;

public class EntityToRestMapper {

    private EntityToRestMapper() {
    }

    public static BookRestDto convertToBook(final Book book) {
        BookRestDto bookRestDto = new BookRestDto();
        bookRestDto.setName(book.getName());
        bookRestDto.setAuthor(book.getAuthor());

        return bookRestDto;
    }
}
