package com.hava.parser;

import com.hava.parser.exceptions.InvalidShapeLabelException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class Circle extends Shape {
    public Circle(String label) throws Exception {
        this(label, Collections.emptyList());
    }

    public Circle(String label, List<Shape> innerShapes) throws Exception {
        this(label, innerShapes, Math.abs(UUID.randomUUID().hashCode()));
    }

    public Circle(String label, List<Shape> innerShapes, int uid) throws Exception {
        super(label, '(', ')', innerShapes, uid);
        if (!Pattern.matches("[A-Z0-9]+", label))
            throw new InvalidShapeLabelException("Only UPPERCASE letters are supported as label for Circles");
    }
}
