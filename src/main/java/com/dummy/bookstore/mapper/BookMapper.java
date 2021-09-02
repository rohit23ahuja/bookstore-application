package com.dummy.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dummy.bookstore.dto.BookDto;
import com.dummy.bookstore.model.Book;

@Mapper
public interface BookMapper {

	@Mapping(target = "quantity", constant = "1")
	Book bookDtoToBook(BookDto bookDto);
}
