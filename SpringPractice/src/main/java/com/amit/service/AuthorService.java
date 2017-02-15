package com.amit.service;

import java.util.List;

import com.amit.domain.AuthorResponseDTO;
import com.amit.domain.CreateAuthorRequestDTO;


public interface AuthorService {
   public CreateAuthorRequestDTO saveNewEntity(CreateAuthorRequestDTO requestData) ;
   public List<AuthorResponseDTO> returnAuthordataSetBasedOnAuthorName(String authorName) throws Exception;
}
