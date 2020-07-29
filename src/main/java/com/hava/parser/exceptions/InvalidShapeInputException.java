package com.hava.parser.exceptions;

public class InvalidShapeInputException extends Exception {
    public InvalidShapeInputException() {
        super("Invalid shape input(s) found");
    }

    public InvalidShapeInputException(String s) {
        super(s);
    }
}
