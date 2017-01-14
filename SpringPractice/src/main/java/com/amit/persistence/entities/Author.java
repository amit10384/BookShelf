package com.amit.persistence.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author", schema = "bookShelf")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String authorId;

	@Column(name = "name")
	private String nameOfAuthor;

	@Column(name = "description")
	private String bookDescription;

	@Column(name = "created_date")
	private String dateAdded;

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getNameOfAuthor() {
		return nameOfAuthor;
	}

	public void setNameOfAuthor(String nameOfAuthor) {
		this.nameOfAuthor = nameOfAuthor;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	

}
