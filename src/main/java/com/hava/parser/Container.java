package com.hava.parser;

import com.hava.parser.exceptions.InvalidShapeInputException;
import com.hava.parser.exceptions.MalformedShapeInputException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Container implements ShapeParser {
    private Collection<Shape> shapes;

    public Container() {
        this(Collections.emptyList());
    }

    public Container(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Collection<Shape> getShapes() {
        return shapes;
    }

    @Override
    public Container parse(String input) throws Exception {
        if (input == null || input.isEmpty()) throw new InvalidShapeInputException();
        // strip off spaces from the input
        String cleansedInput = input.replaceAll("\\s+", "");
        assertIsValidInput(cleansedInput);
        this.shapes = shapeBuilder(cleansedInput);
        return this;
    }

    @Override
    public String toString() {
        // to avoid confusion between square shape start('[') and end(']') labels from list string's '['/']',
        // replace them with '{' for list start and '}' for list end symbols
        return getShapes().toString().replaceAll("^\\[", "{").replaceAll("]$", "}");
    }

    public Collection<Shape> shapeBuilder(String input) throws Exception {
        ArrayList<Shape> shapes = new ArrayList<>();
        char[] chars = input.toCharArray();
        int i = 0;
        while (i < chars.length) {
            switch (chars[i]) {
                case '[':
                    i = Square.createFromInput(input.substring(i), shapes, i);
                    break;
                case '(':
                    // attempt create Circle
                    i = Circle.createFromInput(input.substring(i), shapes, i);
                    break;
                default:
                    // do not thing; probably a space or label text/number
                    if (chars[i] != ' ' && i == 0) {
                        // first character can either be space(' '), '[' or '('
                        throw new MalformedShapeInputException();
                    }
                    i++;
                    break;
            }
        }
        return shapes;
    }

    private void assertIsValidInput(String input) throws InvalidShapeInputException {
        if (Pattern.matches(".*[$@#]+", input)) throw new InvalidShapeInputException();

//        assertValidSquareShape(input);
//        assertValidCircleShape(input);
    }

}
