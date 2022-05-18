package com.dummy.bookstore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "increment")
	@Id
    @GeneratedValue(generator = "generator")
	@Column(name = "BOOK_ID")
	private Long id;

	@Column(name = "ISBN", nullable = false)
	private String isbn;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "AUTHOR", nullable = false)
	private String author;

	@Column(name = "PRICE", nullable = false)
	private Integer price;
	
	@Column(name = "QUANTITY")
	private Integer quantity;
	
	@Version
	private int version;
	
}
