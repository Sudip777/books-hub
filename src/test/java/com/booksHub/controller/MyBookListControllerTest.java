package com.booksHub.controller;

import com.booksHub.service.MyBookListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MyBookListControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MyBookListService myBookListService;

    @InjectMocks
    private com.booksHub.controller.MyBookListController myBookListController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(myBookListController).build();
    }

    @Test
    void testDeleteMyList() throws  Exception{

        int myBookId = 1;
        // perform delete request
        mockMvc.perform(delete("/deleteMyList/{id}",myBookId))
                .andExpect(status().is3xxRedirection()) // expecting a redirection
                .andExpect(redirectedUrl("/my_books")); // expecting a redirect

        // verify the deleteById method was called with the correct book ID
        Mockito.verify(myBookListService).deleteById(myBookId);
    }
}
