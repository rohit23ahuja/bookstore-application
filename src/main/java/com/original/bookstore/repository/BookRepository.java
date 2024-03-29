package com.original.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.original.bookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findFirstByIsbn(String isbn);

	Optional<List<Book>> findByIsbn(String isbn);

	Optional<List<Book>> findByTitleContaining(String title);

	Optional<List<Book>> findByAuthorContaining(String author);

	Optional<Book> findFirstByAuthorAndTitle(String author, String title);
}
