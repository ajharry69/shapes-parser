package com.xently.parser.exceptions;

public class InvalidInnerShapeException extends InvalidShapeException {
    public InvalidInnerShapeException() {
        super("Invalid or unsupported inner shape types");
    }

    public InvalidInnerShapeException(String s) {
        super(s);
    }
}
