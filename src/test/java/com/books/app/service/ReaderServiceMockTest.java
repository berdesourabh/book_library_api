package com.books.app.service;

import com.books.app.domain.Book;
import com.books.app.domain.Reader;
import com.books.app.exception.ApiException;
import com.books.app.model.ReaderDto;
import com.books.app.repository.ReaderRepository;
import com.books.app.service.impl.ReaderServiceImpl;
import com.books.app.testutils.TestDataSupplier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReaderServiceMockTest {

    @Mock
    ReaderRepository mockReaderRepository;

    @Mock
    BookService mockBookService;

    @InjectMocks
    ReaderServiceImpl readerServiceImpl;

    List<Reader> readerData;

    @Before
    public void setUp() {
        setupData();
    }

    @Test
    public void testGetAll_ReturnsAllBooks() {
        when(mockReaderRepository.findAll()).thenReturn(readerData);
        List<Reader> readers = readerServiceImpl.getAll();
        Assert.assertEquals(readers.size(), readerData.size());

    }

    @Test
    public void testCreate_WithValidReaderData_ShouldReturnSucess() {
        Reader newReader = readerData.get(0);
        ReaderDto readerDto = TestDataSupplier.setupReaderDto();
        when(mockReaderRepository.saveAndFlush(any(Reader.class))).thenReturn(newReader);
        Reader reader = readerServiceImpl.create(readerDto);
        Assert.assertEquals(newReader, reader);
    }

    @Test
    public void testAssignBooks_WithValidBookIdsAndReaderId_ShouldReturnSuccess() throws ApiException {
        Reader reader = readerData.get(0);
        Book book = TestDataSupplier.setupBookData();
        List<Long> bookIds = Arrays.asList(1L, 2L);

        when(mockReaderRepository.getOne(anyLong())).thenReturn(reader);
        when(mockBookService.get(anyLong())).thenReturn(book);
        when(mockReaderRepository.saveAndFlush(any(Reader.class))).thenReturn(reader);

        Reader expectedReader = readerServiceImpl.assignBooks(bookIds, 1L);

        Assert.assertEquals(reader, expectedReader);
    }

    private void setupData() {
        readerData = TestDataSupplier.setupReaderList();
    }


}
