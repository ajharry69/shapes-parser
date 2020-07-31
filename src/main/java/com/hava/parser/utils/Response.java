package com.hava.parser.utils;

import com.hava.parser.Shape;

import java.util.List;

public class Response {
    private final List<Shape> shapes;
    private final int nextIndex;

    public Response(List<Shape> shapes, int nextIndex) {
        this.shapes = shapes;
        this.nextIndex = nextIndex;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public int getNextIndex() {
        return nextIndex;
    }
}
