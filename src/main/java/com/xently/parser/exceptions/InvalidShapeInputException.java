package com.xently.parser.exceptions;

public class InvalidShapeInputException extends RuntimeException {
    public InvalidShapeInputException() {
        super("Invalid shape input(s) found");
    }

    public InvalidShapeInputException(String s) {
        super(s);
    }
}
