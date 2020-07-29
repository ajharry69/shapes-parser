package com.hava.parser.exceptions;

public class InvalidShapeException extends Exception {
    public InvalidShapeException() {
        super("Invalid shape.");
    }

    public InvalidShapeException(String s) {
        super(s);
    }
}
