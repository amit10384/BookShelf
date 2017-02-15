package com.amit.persistence.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.amit.persistence.entities.Author;

public interface AuthorRepository extends CrudRepository<Author,String> {

  public List<Author> findByNameOfAuthorIgnoreCase(String authorName);
}