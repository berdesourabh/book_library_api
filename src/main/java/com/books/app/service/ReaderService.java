package com.books.app.service;

import com.books.app.domain.Reader;
import com.books.app.model.ReaderDto;

import java.util.List;

public interface ReaderService {

    List<Reader> getAll();

    Reader create(final ReaderDto reader);

    Reader assignBooks(List<Long> bookIds, final Long readerId);

}
