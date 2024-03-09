package com.bookHub.service;

import com.bookHub.entity.MyBookList;
import com.bookHub.repository.MyBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MyBookListServiceTest {

    @Mock
    private MyBookRepository myBookRepository;

    @InjectMocks
    private MyBookListService myBookListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMyBooks() {
        // Given
        MyBookList book = new MyBookList(1, "Test Book", "Test Author", "10");

        // When
        myBookListService.saveMyBooks(book);

        // Then
        verify(myBookRepository, times(1)).save(book);
    }

    @Test
    void testGetAllMyBooks() {
        // Given
        List<MyBookList> books = new ArrayList<>();
        books.add(new MyBookList(1, "Book1", "Author1", "10"));
        books.add(new MyBookList(2, "Book2", "Author2", "20"));

        when(myBookRepository.findAll()).thenReturn(books);

        // When
        List<MyBookList> result = myBookListService.getAllMyBooks();

        // Then
        assertEquals(2, result.size());
        assertEquals("Book1", result.get(0).getName());
        assertEquals("Book2", result.get(1).getName());
    }

    @Test
    void testDeleteById() {
        // When
        myBookListService.deleteById(1);

        // Then
        verify(myBookRepository, times(1)).deleteById(1);
    }
}
