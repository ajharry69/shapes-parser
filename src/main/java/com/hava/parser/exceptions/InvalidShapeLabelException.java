package com.hava.parser.exceptions;

public class InvalidShapeLabelException extends RuntimeException {
    public InvalidShapeLabelException() {
        super("Invalid shape label.");
    }

    public InvalidShapeLabelException(String s) {
        super(s);
    }
}
