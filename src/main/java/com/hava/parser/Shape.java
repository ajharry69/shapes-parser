package com.hava.parser;

import com.hava.parser.exceptions.InvalidShapeEndLabelException;
import com.hava.parser.exceptions.InvalidShapeException;
import com.hava.parser.exceptions.InvalidShapeStartLabelException;
import com.hava.parser.utils.Constants;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class Shape {
    public static final char[] SUPPORTED_START_LABELS = new char[]{'[', '('};
    public static final char[] SUPPORTED_END_LABELS = new char[]{']', ')'};
    private final int uid;
    private final String label;
    private final char startLabel, endLabel;
    private List<Shape> innerShapes;

    public Shape(String label, char startLabel, char endLabel) {
        this(label, startLabel, endLabel, Collections.emptyList());
    }

    public Shape(String label, char startLabel, char endLabel, List<Shape> innerShapes) {
        this(label, startLabel, endLabel, innerShapes, Math.abs(UUID.randomUUID().hashCode()));
    }

    public Shape(String label, char startLabel, char endLabel, List<Shape> innerShapes, int uid) {
        this.uid = uid;
        if (label == null || label.isEmpty()) throw new InvalidShapeException("A shape must have a label");
        assertSupportedStartLabel(startLabel);
        assertSupportedEndLabel(endLabel);
        this.label = label;
        this.startLabel = startLabel;
        this.endLabel = endLabel;
        this.innerShapes = innerShapes;
    }

    public int getUid() {
        return uid;
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

    public void setInnerShapes(List<Shape> innerShapes) {
        this.innerShapes = innerShapes;
    }

    public List<Shape> addInnerShapes(Shape... shapes) {
        List<Shape> _shapes = Constants.addAll(getInnerShapes(), shapes);
        setInnerShapes(_shapes);
        return _shapes;
    }

    /**
     * Adds [shapes] to this shape as it's inner shapes
     */
    protected void addInnerShapes(List<Shape> shapes, boolean isPendingTraversalsEmpty, boolean pop) {
        if (isPendingTraversalsEmpty) {
            addInnerShapes(shapes.toArray(new Shape[0]));
            shapes.clear();
        } else {
            if (pop) {
                addInnerShapes(shapes.toArray(new Shape[0]));
                shapes.clear();
                shapes.add(this);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape)) return false;

        Shape shape = (Shape) o;

        if (getUid() != shape.getUid()) return false;
        if (getStartLabel() != shape.getStartLabel()) return false;
        if (getEndLabel() != shape.getEndLabel()) return false;
        if (!getLabel().equals(shape.getLabel())) return false;
        return getInnerShapes().equals(shape.getInnerShapes());
    }

    @Override
    public int hashCode() {
        int result = getUid();
        result = 31 * result + getLabel().hashCode();
        result = 31 * result + (int) getStartLabel();
        result = 31 * result + (int) getEndLabel();
        result = 31 * result + getInnerShapes().hashCode();
        return result;
    }

    @Override
    public String toString() {
        List<Shape> shapes = getInnerShapes();
        // to avoid confusion between square shape start('[') and end(']') labels from list string's '['/']',
        // replace them with '{' for list start and '}' for list end symbols
        String shapesStr = shapes.toString().replaceAll("^\\[", "{").replaceAll("]$", "}");
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
