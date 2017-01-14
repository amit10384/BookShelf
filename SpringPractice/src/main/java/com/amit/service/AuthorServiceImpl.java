package com.amit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.common.utils.RandomUIDGenerator;
import com.amit.domain.CreateAuthorRequestDTO;
import com.amit.persistence.entities.Author;
import com.amit.persistence.repo.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Override
	public CreateAuthorRequestDTO saveNewEntity(CreateAuthorRequestDTO requestData) {
		Author author = new Author();
		author.setBookDescription(requestData.getDescription());
		author.setDateAdded(requestData.getDateAdded());
		author.setNameOfAuthor(requestData.getName());
		author.setAuthorId(RandomUIDGenerator.generateId());
		Author authorSaved = authorRepo.save(author);
		requestData.setAuthorId(authorSaved.getAuthorId());
		return requestData;
	}

}
