package com.dummy.bookstore.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
	private List<ErrorItem> errors;

	public ErrorResponse() {
		errors = new ArrayList<>();
	}

	public List<ErrorItem> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorItem> errors) {
		this.errors = errors;
	}

	public void addError(ErrorItem error) {
		errors.add(error);

	}

}
