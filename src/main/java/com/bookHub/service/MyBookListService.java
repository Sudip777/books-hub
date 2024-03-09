package com.bookHub.service;

import com.bookHub.entity.MyBookList;
import com.bookHub.repository.MyBookRepository;
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
