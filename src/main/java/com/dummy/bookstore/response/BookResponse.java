package com.dummy.bookstore.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class BookResponse {

	private final Long id;
	private final String isbn;
	private final String title;
	private final String author;
	private final Integer price;
	private final Integer version;
}
