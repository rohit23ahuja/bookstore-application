package com.dummy.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class SearchBookDto {

	private final String isbn;

	private final String author;

	private final String title;

}
