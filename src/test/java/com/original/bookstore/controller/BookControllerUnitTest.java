package com.original.bookstore.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.original.bookstore.controller.BookController;
import com.original.bookstore.dto.BookDto;
import com.original.bookstore.dto.SearchBookDto;
import com.original.bookstore.model.Book;
import com.original.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerUnitTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	
	@Test
	public void getBooks() throws Exception {
		List<BookDto> bookDtos = getBookDtos();

		SearchBookDto searchBookDto = new SearchBookDto("", "", "");
		
		when(bookService.getBooks()).thenReturn(bookDtos);
		mockMvc.perform(get("/books"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(content().json("[]"));
		//.andExpect(content().string(containsString("Game Of Thrones")));

		verify(bookService, times(1)).findBooks(searchBookDto);
	}
	
	private List<BookDto> getBookDtos() {
        BookDto bookDto = new BookDto("123ABC", "Game Of Thrones", "George Martin", 1700, 1l, 1);
		return Lists.newArrayList(bookDto);
	}

	@Test
	public void addBook() throws Exception{
		Book book = new Book(7l, "42398", "Times", "India", 5, 5, 1);
		mockMvc.perform(post("/books")
				.contentType("application/json")
				.content(asJsonString(book)))
		.andExpect(status().isCreated());
		//verify(bookService, times(1)).addBook(book);
	}
	
	
	@Test
	public void buyBook() throws Exception{
		Book book = new Book(1l, "123ABC", "Game Of Thrones", "George Martin", 1700, 5, 1);
		//when(bookService.buyBook(book)).thenReturn("Book Bought");
		mockMvc.perform(put("/books")
				.contentType("application/json")
				.content(asJsonString(book)))
		.andExpect(status().isOk());
		//.andExpect(content().string(startsWith("Book Bought")));
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  

}
