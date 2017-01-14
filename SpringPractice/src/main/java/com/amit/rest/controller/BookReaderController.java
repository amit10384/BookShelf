package com.amit.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BookReaderController {
	
	@RequestMapping("/test")
	public String testSpringpractice() {
		System.out.println("watsupp biatcch!!!!");
		return "SERVER IS UP!!";
	}

}
