package com.dummy.bookstore.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dummy.bookstore.dto.BookDto;
import com.dummy.bookstore.request.AddBookRequest;
import com.dummy.bookstore.request.BuyBookRequest;
import com.dummy.bookstore.response.BookResponse;

@Mapper
public interface BookDtoMapper {

	BookDto map(AddBookRequest addBookRequest);
	
	BookDto map(BuyBookRequest buyBookRequest);
	
	BookResponse map(BookDto bookDto);
	
	List<BookResponse> map(List<BookDto> bookDtos);
}
