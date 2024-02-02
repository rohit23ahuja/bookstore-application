package com.original.bookstore.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.original.bookstore.dto.BookDto;
import com.original.bookstore.request.AddBookRequest;
import com.original.bookstore.request.BuyBookRequest;
import com.original.bookstore.response.BookResponse;

@Mapper
public interface BookDtoMapper {

	BookDto map(AddBookRequest addBookRequest);
	
	BookDto map(BuyBookRequest buyBookRequest);
	
	BookResponse map(BookDto bookDto);
	
	List<BookResponse> map(List<BookDto> bookDtos);
}
