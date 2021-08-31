package com.dummy.bookstore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public final class Book implements Serializable{

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

	private Book() {
		super();
	}
	

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price
				 + "]";
	}
	
	public static class Builder {
		private String isbn;

		private String title;

		private String author;

		private Integer price;
		
		private Integer quantity;
		
		public Builder() {}
		
		public Builder withIsbn(String isbn) {
			this.isbn = isbn;
			return this;
		}
		
		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}
		
		public Builder withAuthor(String author) {
			this.author = author;
			return this;
		}
		
		public Builder withPrice(Integer price) {
			this.price = price;
			return this;
		}
		
		public Builder withQuantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}
	}

}
