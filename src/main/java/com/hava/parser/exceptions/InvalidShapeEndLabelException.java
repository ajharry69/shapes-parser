package com.hava.parser.exceptions;

public class InvalidShapeEndLabelException extends InvalidShapeLabelException {
    public InvalidShapeEndLabelException() {
        super("Invalid shape end label");
    }

    public InvalidShapeEndLabelException(String s) {
        super(s);
    }
}
