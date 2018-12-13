package com.safframework.bytekit.exception;

/**
 * Created by tony on 2018-12-10.
 */
public class BytesException extends RuntimeException {

    private static final long serialVersionUID = 3192856874862348751L;

    public BytesException(String message) {
        super(message);
    }

    public BytesException(Throwable throwable) {
        super(throwable);
    }

    public BytesException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
