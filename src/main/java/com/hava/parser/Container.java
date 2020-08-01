package com.hava.parser;

import com.hava.parser.exceptions.InvalidShapeInputException;
import com.hava.parser.exceptions.MalformedShapeInputException;
import com.hava.parser.utils.Response;

import java.util.*;
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
            char c = chars[i];
            Response response;
            switch (c) {
                case '[':
                    response = Square.createFromInput(input.substring(i));
                    shapes.addAll(response.getShapes());
                    i += response.getSkipElements();
                    break;
                case '(':
                    // attempt create Circle
                    response = Circle.createFromInput(input.substring(i));
                    shapes.addAll(response.getShapes());
                    i += response.getSkipElements();
                    break;
                default:
                    // first character can either be space(' '), '[' or '('
//                    if (!Arrays.asList(']', ')').contains(c)) throw new MalformedShapeInputException();
                    i++;
                    break;
            }
        }
        return shapes;
    }

    private void assertIsValidInput(String input) throws InvalidShapeInputException {
        if (Pattern.matches(".*[$@#]+", input)) throw new InvalidShapeInputException();
    }

}
