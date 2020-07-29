package com.hava.parser.exceptions;

public class MalformedShapeInputException extends InvalidShapeInputException {
    public MalformedShapeInputException() {
        super("Malformed shape input. No corresponding end/start label found");
    }

    public MalformedShapeInputException(String s) {
        super(s);
    }
}
