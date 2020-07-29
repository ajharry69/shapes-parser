package com.hava.parser;

import com.hava.parser.exceptions.InvalidShapeEndLabelException;
import com.hava.parser.exceptions.InvalidShapeException;
import com.hava.parser.exceptions.InvalidShapeStartLabelException;

import java.util.Collections;
import java.util.List;

public class Shape {
    public static final char[] SUPPORTED_START_LABELS = new char[]{'[', '('};
    public static final char[] SUPPORTED_END_LABELS = new char[]{']', ')'};
    private final String label;
    private final char startLabel, endLabel;
    private final List<Shape> innerShapes;

    public Shape(String label, char startLabel, char endLabel, List<Shape> innerShapes) throws Exception {
        if (label == null || label.isEmpty()) throw new InvalidShapeException("A shape must have a label");
        assertSupportedStartLabel(startLabel);
        assertSupportedEndLabel(endLabel);
        this.label = label;
        this.startLabel = startLabel;
        this.endLabel = endLabel;
        this.innerShapes = innerShapes;
    }

    public Shape(String label, char startLabel, char endLabel) throws Exception {
        this(label, startLabel, endLabel, Collections.emptyList());
    }

    public String getLabel() {
        return label;
    }

    public char getStartLabel() {
        return startLabel;
    }

    public char getEndLabel() {
        return endLabel;
    }

    public List<Shape> getInnerShapes() {
        return innerShapes;
    }

    @Override
    public String toString() {
        List<Shape> shapes = getInnerShapes();
        String shapesStr = shapes.toString();
        if (shapes.isEmpty()) shapesStr = "";
        return String.format("%s %s %s %s", getStartLabel(), getLabel(), shapesStr, getEndLabel()).replaceAll(" {2}", " ");
    }

    private void assertSupportedStartLabel(char startLabel) throws InvalidShapeStartLabelException {
        boolean isShapeStartLabelSupported;
        for (int i = 0; i < SUPPORTED_START_LABELS.length; i++) {
            isShapeStartLabelSupported = SUPPORTED_START_LABELS[i] == startLabel;
            if (isShapeStartLabelSupported) break;
            if (i == SUPPORTED_START_LABELS.length - 1) throw new InvalidShapeStartLabelException();
        }
    }

    private void assertSupportedEndLabel(char endLabel) throws InvalidShapeEndLabelException {
        boolean isShapeEndLabelSupported;
        for (int i = 0; i < SUPPORTED_END_LABELS.length; i++) {
            isShapeEndLabelSupported = SUPPORTED_END_LABELS[i] == endLabel;
            if (isShapeEndLabelSupported) break;
            if (i == SUPPORTED_END_LABELS.length - 1) throw new InvalidShapeEndLabelException();
        }
    }
}
