package com.dummy.bookstore.service;

import java.util.List;

import com.dummy.bookstore.dto.BookDto;
import com.dummy.bookstore.dto.SearchBookDto;
import com.dummy.bookstore.model.Book;

public interface BookService {
	public List<Book> getBooks();
	public Book addBook(BookDto bookDto);
	public String buyBook(Book book);
	public Book findByIsbn(String searchString);
	public List<Book> findBooks(SearchBookDto searchBookDto);
	public List<String> findPostsByIsbn(String isbn);
}
