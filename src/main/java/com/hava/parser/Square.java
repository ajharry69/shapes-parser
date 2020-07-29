package com.hava.parser;

import com.hava.parser.exceptions.InvalidInnerShapeException;
import com.hava.parser.exceptions.InvalidShapeLabelException;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Square extends Shape {
    public Square(String label) throws Exception {
        this(label, Collections.emptyList());
    }

    public Square(String label, List<Shape> innerShapes) throws Exception {
        super(label, '[', ']', innerShapes);
        if (!(Pattern.matches("^[0-9]+$", label)))
            throw new InvalidShapeLabelException("Square can only be labeled with a number");
        for (Shape shape : innerShapes) {
            if (!(shape instanceof Square))
                throw new InvalidInnerShapeException("Square can only contain other squares");
        }
    }
}
