package com.dummy.bookstore.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

import com.dummy.bookstore.dto.BookDto;
import com.dummy.bookstore.dto.SearchBookDto;
import com.dummy.bookstore.exception.ResourceNotFoundException;
import com.dummy.bookstore.exception.SoldOutException;
import com.dummy.bookstore.exception.StaleException;
import com.dummy.bookstore.mapper.BookMapper;
import com.dummy.bookstore.model.Book;
import com.dummy.bookstore.model.Post;
import com.dummy.bookstore.repository.BookRepository;
import com.dummy.bookstore.service.BookService;
import com.dummy.bookstore.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
@Slf4j
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PostService postService;
	
	@Autowired
	private BookMapper bookMapper;

	@Override
	public List<BookDto> getBooks() {
		List<Book> books = bookRepository.findAll();
		return bookMapper.map(books);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 30)
	public BookDto addBook(BookDto bookDto) {
		log.info("Adding book to store");
		
		Optional<Book> book = bookRepository.findFirstByIsbn(bookDto.getIsbn());
		if (book.isPresent()) {
			log.info("Book exists updating quantity");
			Book existing = book.get();
			existing.setQuantity(existing.getQuantity() + 1);
			return bookMapper.map(existing);
		}
		
		book = bookRepository.findFirstByAuthorAndTitle(bookDto.getAuthor(), bookDto.getTitle());
		if (book.isPresent()) {
			log.info("Book exists updating quantity");
			Book existing = book.get();
			existing.setQuantity(existing.getQuantity() + 1);
			return bookMapper.map(existing);
		}

		Book saved = bookRepository.save(bookMapper.map(bookDto));
		log.info("Added Book");
		return bookMapper.map(saved);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 30)
	public BookDto buyBook(BookDto bookDto) {
		log.info("Buying book id - " + bookDto.getId());
		Book b = bookRepository.findById(bookDto.getId()).orElseThrow(
				() -> new ResourceNotFoundException("No Book Found"));
		if (b.getQuantity() <= 0) {
			throw new SoldOutException("Item sold out");
		}
		if (b.getVersion() != bookDto.getVersion()) {
			throw new StaleException(
					"You are working on old version, please refresh and retry");
		}
		b.setQuantity(b.getQuantity() - 1);
		Book saved = bookRepository.save(b);
		log.info("Book Bought, available quantity " + b.getQuantity());
		return bookMapper.map(saved);
	}

	@Override
	public BookDto findByIsbn(String searchString) {
		Book book = bookRepository.findFirstByIsbn(searchString).orElseThrow(
				() -> new ResourceNotFoundException("Invalid Isbn"));
		return bookMapper.map(book);
	}

	@Override
	public List<BookDto> findBooks(SearchBookDto searchBookDto) {
		List<Book> result;
		String isbn = searchBookDto.getIsbn();
		String author = searchBookDto.getAuthor();
		String title = searchBookDto.getTitle();
		if (StringUtils.isNotBlank(isbn)) {
			log.info("Find books based on isbn " + isbn);
			result = bookRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Invalid Isbn"));
		} else if (StringUtils.isNotBlank(author)) {
			log.info("Find books based on author " + author);
			result = bookRepository.findByAuthorContaining(author)
					.orElseThrow(() -> new ResourceNotFoundException("Invalid Author"));
		} else if (StringUtils.isNotBlank(title)) {
			log.info("Find books based on title " + title);
			result = bookRepository.findByTitleContaining(title)
					.orElseThrow(() -> new ResourceNotFoundException("Invalid title"));
		} else {
			log.info("Find books all");
			result = bookRepository.findAll();
		}
		return bookMapper.map(result);
	}

	@Override
	public List<String> findPostsByIsbn(String isbn) {
		log.info("Finding post for isbn - " + isbn);
		CompletableFuture<Post[]> completableFuture = postService
				.findPosts(isbn);
		BookDto bookDto = findByIsbn(isbn);
		Post[] posts = new Post[1];
		try {
			posts = completableFuture.get();
		} catch (InterruptedException e) {
			log.error("Unable to get data from posts api", e);
			throw new ResourceNotFoundException(
					"Unable to get data from posts api");
		} catch (ExecutionException e) {
			log.error("Unable to get data from posts api", e);
			throw new ResourceNotFoundException(
					"Unable to get data from posts api");
		}
		List<String> result = new ArrayList<String>();
		Arrays.stream(posts)
				.filter(p -> StringUtils.contains(p.getTitle(), bookDto.getTitle())
						|| StringUtils.contains(p.getBody(), bookDto.getTitle()))
				.forEach(p -> result.add(p.getTitle()));
		return result;
	}

}
