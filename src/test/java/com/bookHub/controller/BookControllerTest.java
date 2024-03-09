package com.bookHub.controller;

import com.bookHub.entity.Book;
import com.bookHub.entity.MyBookList;
import com.bookHub.service.BookService;
import com.bookHub.service.MyBookListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }


    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void testBookRegister() throws Exception {
        mockMvc.perform((get("/book_register")))
                .andExpect(status().isOk())
                .andExpect(view().name("bookRegister"));
    }

    @Test
    void testGetAllBook() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Author1", "10"));
        books.add(new Book(2, "Book2", "Author2", "20"));

        // Set a breakpoint here to inspect the behavior of the mock
        when(bookService.getAllBook()).thenReturn(books);

        // Set a breakpoint here to inspect the behavior of the mockMvc
        mockMvc.perform(get("/available_books"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookList"))
                .andExpect(model().attribute("book", books));
    }

    @Test
    void testAddBook() throws Exception {
        // Mocking the BookService
        BookService bookService = Mockito.mock(BookService.class);
        BookController bookController = new BookController(bookService, null); // Assuming no dependency on MyBookListService

        // Setting up MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        // Creating a sample book
        Book book = new Book();
        book.setId(1);
        book.setName("Test Book");
        book.setAuthor("Test Author");
        book.setPrice("10");

        // Mocking the behavior of the bookService.save() method
        doNothing().when(bookService).save(book);

        // Performing a POST request to /save with the book data
        mockMvc.perform(post("/save")
                        .param("id", "1")
                        .param("name", "Test Book")
                        .param("author", "Test Author")
                        .param("price", "10"))
                .andExpect(status().is3xxRedirection()) // Expecting a redirect
                .andExpect(redirectedUrl("/available_books")); // Expecting redirection to /available_books
    }

    @Test
    void testGetMyList() throws Exception {
        // Mocking the dependencies
        BookService bookService = Mockito.mock(BookService.class);
        MyBookListService myBookListService = Mockito.mock(MyBookListService.class);
        BookController bookController = new BookController(bookService, myBookListService);

        // Setting up MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        // Creating a sample book
        int bookId = 1;
        Book book = new Book();
        book.setId(bookId);
        book.setName("Test Book");
        book.setAuthor("Test Author");
        book.setPrice("10");

        // Creating a sample myBookList
        MyBookList myBookList = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());

        // Mocking the behavior of bookService.getBookById()
        when(bookService.getBookById(bookId)).thenReturn(book);

        // Performing a GET request to /mylist/{id}
        mockMvc.perform(get("/mylist/{id}", bookId))
                .andExpect(status().is3xxRedirection()) // Expecting a redirect
                .andExpect(redirectedUrl("/my_books")); // Expecting redirection to /my_books

    }



    @Test
    public void testEditBook() throws Exception {
        int bookId = 1; // Specify the book ID for testing

        // Mock data for the book
        Book mockBook = new Book(bookId, "Test Book", "Test Author", "20.00");

        // Mock the service method to return the mock book
        Mockito.when(bookService.getBookById(bookId)).thenReturn(mockBook);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/editBook/{id}", bookId))
                .andExpect(status().isOk())  // Expecting HTTP 200 OK status
                .andExpect(view().name("bookEdit")) // Expecting the view name to be "bookEdit"
                .andExpect(model().attributeExists("book")); // Expecting the "book" attribute to exist in the model
    }

    @Test
    public void testDelete() throws Exception{
        int bookId = 1;

        // perform delete request
        mockMvc.perform(delete("/deleteBook/{id}",bookId))
                .andExpect(status().is3xxRedirection()) // expecting a redirection
                .andExpect(redirectedUrl("/available_books")); // expecting a redirect

        // verify the deleteById method was called with the correct book ID
        Mockito.verify(bookService).deleteById(bookId);
    }





    // Add more tests for other controller methods as needed
}
