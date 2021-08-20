package com.nagarro.bookstore.repository;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dummy.bookstore.model.Book;
import com.dummy.bookstore.repository.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void whenFindByIsbn_thenReturnBook() {
		Book result = bookRepository.findFirstByIsbn("123ABC").get();
		assertThat(result.getIsbn(), equalTo("123ABC"));
	}
	
	@Test
	public void whenFindByTitle_thenReturnBook() {
		String searchString = "Game";
		List<Book> result = bookRepository.findByTitleContaining(searchString).get();
		
		assertThat(result.get(0).getTitle(), containsString(searchString));
	}
	
	@Test
	public void whenFindByAuthor_thenReturnBook() {
		String searchString = "George";
		List<Book> result = bookRepository.findByAuthorContaining(searchString).get();
		
		assertThat(result.get(0).getAuthor(), containsString(searchString));
	}
}
