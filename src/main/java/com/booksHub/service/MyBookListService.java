package com.booksHub.service;

import com.booksHub.entity.MyBookList;
import com.booksHub.repository.MyBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {


	private final MyBookRepository myBookRepository;

	public MyBookListService(MyBookRepository myBookRepository) {
		this.myBookRepository = myBookRepository;
	}


	public void saveMyBooks(MyBookList book) {
		myBookRepository.save(book);
	}
	
	public List<MyBookList> getAllMyBooks(){
		return myBookRepository.findAll();
	}
	
	public void deleteById(int id) {
		myBookRepository.deleteById(id);
	}
}
