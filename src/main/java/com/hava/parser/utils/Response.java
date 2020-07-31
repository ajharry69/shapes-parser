package com.hava.parser.utils;

import com.hava.parser.Shape;

import java.util.List;

public class Response {
    private final List<Shape> shapes;
    private final int skipElements;

    public Response(List<Shape> shapes, int skipElements) {
        this.shapes = shapes;
        this.skipElements = skipElements;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public int getSkipElements() {
        return skipElements;
    }
}
