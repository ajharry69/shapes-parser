package com.xently.parser.exceptions;

public class InvalidShapeException extends RuntimeException {
    public InvalidShapeException() {
        super("Invalid shape.");
    }

    public InvalidShapeException(String s) {
        super(s);
    }
}
