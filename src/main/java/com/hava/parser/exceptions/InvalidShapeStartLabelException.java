package com.hava.parser.exceptions;

public class InvalidShapeStartLabelException extends InvalidShapeLabelException {
    public InvalidShapeStartLabelException() {
        super("Invalid shape start label");
    }

    public InvalidShapeStartLabelException(String s) {
        super(s);
    }
}
