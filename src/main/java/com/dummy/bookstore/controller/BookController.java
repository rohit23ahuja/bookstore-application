package com.dummy.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dummy.bookstore.model.Book;
import com.dummy.bookstore.service.BookService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	/**
	 * Api to search books based on either one of - isbn, author or title. If none
	 * of the above params are passed it returns all the books.
	 * 
	 * For no match found scenario for a particular param - a dummy list is
	 * returned.
	 * 
	 * @param isbn
	 * @param author
	 * @param title
	 * @return
	 */
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks(@RequestParam(value = "isbn", required = false) String isbn,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "title", required = false) String title) {
		return new ResponseEntity<List<Book>>(bookService.findBooks(isbn, author, title), HttpStatus.OK);
	}

	/**
	 * Api to add a book in the book store. If book already exists in the system
	 * quantity is updated.
	 * 
	 * @param book
	 * @return
	 */
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody @Valid Book book) {
		return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.CREATED);
	}

	/**
	 * Api to buy a book. Quantity is decremented by 1 for each call. It checks
	 * whether relevant quantity available for purchase or not. It checks whether
	 * user has an old state of book data by doing Hibernate version validation.
	 * 
	 * @param book
	 * @return
	 */
	@PutMapping("/books")
	public ResponseEntity<String> buyBook(@RequestBody @Valid Book book) {
		return new ResponseEntity<String>(bookService.buyBook(book), HttpStatus.OK);
	}

	/**
	 * Api to fetch media coverage about a book - given its isbn
	 * 
	 * @param isbn
	 * @return
	 */
	@GetMapping("/books/{isbn}/posts")
	public ResponseEntity<List<String>> searchPostsByIsbn(@PathVariable(value = "isbn", required = true) String isbn) {
		return new ResponseEntity<List<String>>(bookService.findPostsByIsbn(isbn), HttpStatus.OK);
	}

}
