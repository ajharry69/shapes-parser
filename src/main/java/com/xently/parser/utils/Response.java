package com.xently.parser.utils;

import com.xently.parser.Shape;

import java.util.List;

public class Response {
    private final List<Shape> shapes;
    private final int traversedCharsCount;

    public Response(List<Shape> shapes, int traversedCharsCount) {
        this.shapes = shapes;
        this.traversedCharsCount = traversedCharsCount;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public int getTraversedCharsCount() {
        return traversedCharsCount;
    }

    @Override
    public String toString() {
        return String.format("Skip Elements: %d, Shapes: %s", getTraversedCharsCount(), getShapes());
    }
}
