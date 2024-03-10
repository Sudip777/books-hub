package com.booksHub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booksHub.service.MyBookListService;

@Controller
public class MyBookListController {

	private final MyBookListService myBookListService;

	public MyBookListController(MyBookListService myBookListService) {
		this.myBookListService = myBookListService;
	}

	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		myBookListService.deleteById(id);
		return "redirect:/my_books";
	}
}
