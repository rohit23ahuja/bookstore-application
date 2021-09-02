package com.dummy.bookstore.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class AddBookRequest {

	@NotNull(message = "Isbn is required")
	private final String isbn;

	@NotNull(message = "Title is required")
	private final String title;

	@NotNull(message = "Author is required")
	private final String author;

	@NotNull(message = "Price is required")
	private final Integer price;

}
