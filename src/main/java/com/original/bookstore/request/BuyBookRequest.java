package com.original.bookstore.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class BuyBookRequest {

	@NotNull(message = "Id is required")
	private final Long id;

	@NotNull(message = "Version is required")
	private final Integer version;
}
