package com.dummy.bookstore.service;

import java.util.List;

import com.dummy.bookstore.dto.BookDto;
import com.dummy.bookstore.dto.SearchBookDto;

public interface BookService {
	public List<BookDto> getBooks();
	public BookDto addBook(BookDto bookDto);
	public BookDto buyBook(BookDto bookDto);
	public BookDto findByIsbn(String searchString);
	public List<BookDto> findBooks(SearchBookDto searchBookDto);
	public List<String> findPostsByIsbn(String isbn);
}
