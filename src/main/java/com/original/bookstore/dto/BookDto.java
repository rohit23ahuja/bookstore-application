package com.original.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class BookDto {

	private final String isbn;

	private final String title;

	private final String author;

	private final Integer price;
	
	private final Long id;
	
	private final Integer version;

}
