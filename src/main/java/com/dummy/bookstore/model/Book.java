package com.dummy.bookstore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_ID")
	private Long id;

	@Column(name = "ISBN", nullable = false)
	@NotNull(message="Isbn is required")
	private String isbn;

	@Column(name = "TITLE", nullable = false)
	@NotNull(message="Title is required")
	private String title;

	@Column(name = "AUTHOR", nullable = false)
	@NotNull(message="Author is required")
	private String author;

	@Column(name = "PRICE", nullable = false)
	@NotNull(message="Price is required")
	private Integer price;
	
	@Column(name = "QUANTITY")
	private Integer quantity;
	
	@Version
	private int version;

	public Book() {
		super();
	}

	public Book(Long id, String isbn, String title, String author, Integer price, Integer quantity) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity=quantity;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price
				 + "]";
	}

	public Long getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Integer getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
