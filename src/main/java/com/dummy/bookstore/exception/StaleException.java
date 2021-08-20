package com.dummy.bookstore.exception;

public class StaleException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public StaleException() {
        super();
    }

    public StaleException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public StaleException(final String message) {
        super(message);
    }

    public StaleException(final Throwable cause) {
        super(cause);
    }
}
