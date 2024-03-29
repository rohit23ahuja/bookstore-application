package com.original.bookstore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.original.bookstore.dto.BookDto;
import com.original.bookstore.model.Book;

@Mapper
public interface BookMapper {

	@Mapping(target = "quantity", constant = "1")
	Book map(BookDto bookDto);
	
	BookDto map(Book book);
	
	List<BookDto> map(List<Book> books);
	
}
