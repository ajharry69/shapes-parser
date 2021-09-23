package com.xently.parser.exceptions;

public class InvalidShapeStartLabelException extends InvalidShapeLabelException {
    public InvalidShapeStartLabelException() {
        super("Invalid shape start label");
    }

    public InvalidShapeStartLabelException(String s) {
        super(s);
    }
}
