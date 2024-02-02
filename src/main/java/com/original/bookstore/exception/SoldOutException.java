package com.original.bookstore.exception;

public class SoldOutException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public SoldOutException() {
        super();
    }

    public SoldOutException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SoldOutException(final String message) {
        super(message);
    }

    public SoldOutException(final Throwable cause) {
        super(cause);
    }
}
