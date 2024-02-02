package com.original.bookstore.mapper;

import java.util.Map;

import org.mapstruct.Mapper;

import com.original.bookstore.dto.SearchBookDto;

@Mapper
public interface SearchBookDtoMapper {

	default SearchBookDto map(
			Map<String, String> requestParams) {
		return new SearchBookDto(requestParams.get("isbn"),
				requestParams.get("author"), requestParams.get("title"));
	}

}
