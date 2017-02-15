package com.amit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.common.constant.CommonConstant;
import com.amit.common.utils.RandomUIDGenerator;
import com.amit.domain.AuthorResponseDTO;
import com.amit.domain.CreateAuthorRequestDTO;
import com.amit.persistence.entities.Author;
import com.amit.persistence.repo.AuthorRepository;
import com.amit.query.runner.NativeQueryRunnerImpl;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private NativeQueryRunnerImpl nativeQueryImpl;
	
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

	@Override
	public List<AuthorResponseDTO> returnAuthordataSetBasedOnAuthorName(String authorName) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("authorName", authorName);
		return nativeQueryImpl.returnAuthorListIfNameMatches(queryParams, CommonConstant.SEARCH_BY_AUTHOR_NAME_QUERY);
	}

}
