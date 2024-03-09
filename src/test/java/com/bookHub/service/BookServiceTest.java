package com.bookHub.service;

import com.bookHub.entity.Book;
import com.bookHub.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Given
        Book book = new Book(1, "Test Book", "Test Author", "10");

        // When
        bookService.save(book);

        // Then
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetAllBook() {
        // Given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Author1", "10"));
        books.add(new Book(2, "Book2", "Author2", "20"));

        when(bookRepository.findAll()).thenReturn(books);

        // When
        List<Book> result = bookService.getAllBook();

        // Then
        assertEquals(2, result.size());
        assertEquals("Book1", result.get(0).getName());
        assertEquals("Book2", result.get(1).getName());
    }

    @Test
    void testGetBookById() {
        // Given
        Book book = new Book(1, "Test Book", "Test Author", "10");
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // When
        Book result = bookService.getBookById(1);

        // Then
        assertEquals(book, result);
    }

    @Test
    void testDeleteById() {
        // When
        bookService.deleteById(1);

        // Then
        verify(bookRepository, times(1)).deleteById(1);
    }
}
