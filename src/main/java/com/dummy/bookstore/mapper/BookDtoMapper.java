package com.dummy.bookstore.mapper;

import org.mapstruct.Mapper;

import com.dummy.bookstore.dto.BookDto;
import com.dummy.bookstore.request.AddBookRequest;

@Mapper
public interface BookDtoMapper {

	BookDto addBookRequestToBookDto(AddBookRequest addBookRequest);
}
