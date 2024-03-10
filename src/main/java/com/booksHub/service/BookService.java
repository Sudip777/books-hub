package com.booksHub.service;

import com.booksHub.entity.Book;
import com.booksHub.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
	

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository){
		this.bookRepository = bookRepository;

	}

	public void save(Book b) {
		bookRepository.save(b);
	}
	
	public List<Book> getAllBook(){
		return bookRepository.findAll();
	}
	
	public Book getBookById(int id) {
		return bookRepository.findById(id).get();
	}
	public void deleteById(int id) {
		bookRepository.deleteById(id);
	}
}
