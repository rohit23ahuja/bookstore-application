package com.dummy.bookstore.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dummy.bookstore.exception.ResourceNotFoundException;
import com.dummy.bookstore.exception.SoldOutException;
import com.dummy.bookstore.exception.StaleException;
import com.dummy.bookstore.model.Book;
import com.dummy.bookstore.model.Post;
import com.dummy.bookstore.repository.BookRepository;
import com.dummy.bookstore.service.BookService;
import com.dummy.bookstore.service.PostService;

@Service
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public class BookServiceImpl implements BookService {
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PostService postService;

	@Override
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED, readOnly=false, timeout=30)
	public Book addBook(Book book) {
		logger.info("Adding book to store");
		if (book.getId()!=null) {
			logger.info("Book exists updating quantity");
			Book existing = bookRepository.findById(book.getId()).orElseThrow(()->new ResourceNotFoundException("No Book Found"));
			book.setQuantity(existing.getQuantity()+1);
		}
		Book saved = bookRepository.save(book);
		logger.info("Added Book");
		return saved;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED, readOnly=false, timeout=30)
	public String buyBook(Book book) {
		logger.info("Buying book id - "+book.getId());
		Book b = bookRepository.findById(book.getId()).orElseThrow(()->new ResourceNotFoundException("No Book Found"));
		if (b.getQuantity()<=0) {
			throw new SoldOutException("Item sold out");
		}
		if(b.getVersion()!=book.getVersion()) {
			throw new StaleException("You are working on old version, please refresh and retry");
		}
		b.setQuantity(b.getQuantity()-1);
		bookRepository.save(b);
		logger.info("Book Bought, available quantity "+book.getQuantity());
		return "Book Bought";
	}
	
	@Override
	public Book findByIsbn(String searchString) {
		return bookRepository.findFirstByIsbn(searchString).orElseThrow(()->new ResourceNotFoundException("Invalid Isbn"));
	}

	@Override
	public List<Book> findBooks(String isbn, String author, String title) {
		List<Book> dummyResult = new ArrayList<Book>();
		if (StringUtils.isNotBlank(isbn)) {
			logger.info("Find books based on isbn "+isbn);
			return bookRepository.findByIsbn(isbn).orElse(dummyResult);
		} else if(StringUtils.isNotBlank(author)) {
			logger.info("Find books based on author "+author);
			return bookRepository.findByAuthorContaining(author).orElse(dummyResult);
		} else if(StringUtils.isNotBlank(title)) {
			logger.info("Find books based on title "+title);
			return bookRepository.findByTitleContaining(title).orElse(dummyResult);
		} else {
			logger.info("Find books all");
			return bookRepository.findAll();
		}
	}
	
	@Override
	public List<String> findPostsByIsbn(String isbn) {
		logger.info("Finding post for isbn - " + isbn);
		CompletableFuture<Post[]> completableFuture = postService.findPosts(isbn);
		Book book = findByIsbn(isbn);
		Post[] posts = new Post[1];
		try {
			posts = completableFuture.get();
		} catch (InterruptedException e) {
			logger.error("Unable to get data from posts api", e);
			throw new ResourceNotFoundException("Unable to get data from posts api");
		} catch (ExecutionException e) {
			logger.error("Unable to get data from posts api", e);
			throw new ResourceNotFoundException("Unable to get data from posts api");
		}
		List<String> result = new ArrayList<String>();
		Arrays.stream(posts).filter(p -> StringUtils.contains(p.getTitle(), book.getTitle())
				|| StringUtils.contains(p.getBody(), book.getTitle())).forEach(p -> result.add(p.getTitle()));
		return result;
	}

}
