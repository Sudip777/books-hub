package com.bookHub.service;

import com.bookHub.entity.Book;
import com.bookHub.repository.BookRepository;
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
