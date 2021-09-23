package com.xently.parser.exceptions;

public class InvalidShapeEndLabelException extends InvalidShapeLabelException {
    public InvalidShapeEndLabelException() {
        super("Invalid shape end label");
    }

    public InvalidShapeEndLabelException(String s) {
        super(s);
    }
}
