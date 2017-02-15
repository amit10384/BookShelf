package com.amit.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amit.domain.AuthorResponseDTO;
import com.amit.domain.CreateAuthorRequestDTO;
import com.amit.service.AuthorService;

@RestController
@RequestMapping("/")
public class BookReaderController {
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping("/test")
	public String testSpringpractice() {
		System.out.println("watsupp biatcch!!!!");
		return "SERVER IS UP!!";
	}
	
	@RequestMapping(value="/saveAuthorData" ,method=RequestMethod.POST)
	public ResponseEntity<CreateAuthorRequestDTO> populateDataInsideAuthorTable(@RequestBody CreateAuthorRequestDTO request) {
		System.out.println("watsupp biatcch!!!!");
		ResponseEntity<CreateAuthorRequestDTO> response = null;
		CreateAuthorRequestDTO requestData =null;
		try {
			requestData = authorService.saveNewEntity(request);
		    response = new ResponseEntity<CreateAuthorRequestDTO>(requestData,HttpStatus.OK); 
		}catch(Exception e) {
			System.out.println("fucked up!!!");
			response = new ResponseEntity<CreateAuthorRequestDTO>(requestData,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return response;
	}
	
	
	@RequestMapping(value="/returnAuthorData/{authorName}" ,method=RequestMethod.GET)
	public ResponseEntity<List<AuthorResponseDTO>> retrieveAuthorData(@PathVariable("authorName") String authorName ) {
		System.out.println("watsupp biatcch!!!!");
		ResponseEntity<List<AuthorResponseDTO>> response = null;
		List<AuthorResponseDTO> resp =null;
		try {
			resp = authorService.returnAuthordataSetBasedOnAuthorName(authorName);
		    response = new ResponseEntity<List<AuthorResponseDTO>>(resp,HttpStatus.OK); 
		}catch(Exception e) {
			System.out.println("fucked up!!!");
			response = new ResponseEntity<List<AuthorResponseDTO>>(resp,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return response;
	}

}
