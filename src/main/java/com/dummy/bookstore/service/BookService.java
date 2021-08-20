package com.dummy.bookstore.service;

import java.util.List;

import com.dummy.bookstore.model.Book;

public interface BookService {
	public List<Book> getBooks();
	public Book addBook(Book book);
	public String buyBook(Book book);
	public Book findByIsbn(String searchString);
	public List<Book> findBooks(String isbn, String author, String title);
	public List<String> findPostsByIsbn(String isbn);
}
