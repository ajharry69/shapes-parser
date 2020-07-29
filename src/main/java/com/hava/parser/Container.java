package com.hava.parser;

import com.hava.parser.exceptions.InvalidShapeInputException;
import com.hava.parser.exceptions.MalformedShapeInputException;

import java.util.*;
import java.util.regex.Pattern;

import static com.hava.parser.utils.Constants.assertValidSquareShape;

public class Container implements ShapeParser {
    private List<Shape> shapes;

    public Container() {
        this(Collections.emptyList());
    }

    public Container(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public Container parse(String input) throws Exception {
        assertIsValidInput(input);
        this.shapes = shapeBuilder(input);
        return this;
    }

    @Override
    public String toString() {
        return this.getShapes().toString();
    }

    public List<Shape> shapeBuilder(String input) throws Exception {
        // visited start label indices
        Set<Integer> visitedSLIndices = new HashSet<>();
        List<Shape> shapes = new ArrayList<>();
        char[] inputChars = input.toCharArray();
        for (int i = 0; i < inputChars.length; i++) {
            // System.out.printf("<<<< (i = %d), (visited = %s) >>>>%n", i, visitedSLIndices); // TODO
            if (visitedSLIndices.contains(i)) continue;
            switch (inputChars[i]) {
                case '[':
                    createInnerShapes(i, input, visitedSLIndices, shapes);
                    break;
                case '(':
                    // attempt create Circle
                    break;
                default:
                    // do not thing; probably a space or label text/number
                    if (inputChars[i] != ' ' && i == 0) {
                        // first character can either be space(' '), '[' or '('
                        throw new MalformedShapeInputException();
                    }
                    break;
            }
        }
        return shapes;
    }

    private Square createSquare(int i, String input, Set<Integer> visitedSLIndices) throws Exception {
        visitedSLIndices.add(i);
        List<Square> squares = new ArrayList<>();
        List<Shape> innerShapes = new ArrayList<>();
        StringBuilder shapeLabelBuilder = new StringBuilder();
        char[] charArray = input.substring(i + 1).toCharArray();
        for (int j = 0; j < charArray.length; j++) {
            char c = charArray[j];
            String label = shapeLabelBuilder.toString();
            if (Pattern.matches("[0-9]", String.valueOf(c))) {
                shapeLabelBuilder.append(c);// valid square label
            } else if (c == ']') {
                int i1 = j + 1;
                while (i1 < charArray.length && charArray[i1] == '[') {
                    // square can have more than one square
                    createInnerShapes(i1, input, visitedSLIndices, innerShapes);
                    i1++;
                }
                break; // attempt create Square
            } else if (c == '[') {
                if (squares.size() == 0) squares.add(new Square(label, innerShapes));
                createInnerShapes(j + 1, input, visitedSLIndices, innerShapes);
            } else {
                // invalid Square label
                throw new MalformedShapeInputException();
            }
        }
        if (squares.size() == 0) squares.add(new Square(shapeLabelBuilder.toString(), innerShapes));
        return squares.get(0);
    }

    private void createInnerShapes(int i, String input, Set<Integer> visitedSLIndices, List<Shape> innerShapes) throws Exception {
        if (!visitedSLIndices.contains(i)) {
            // an inner Square, redo the process while adding to a list
            innerShapes.add(createSquare(i, input, visitedSLIndices));
        }
    }

    private void assertIsValidInput(String input) throws InvalidShapeInputException {
        if (Pattern.matches(".*[$@#]+", input)) throw new InvalidShapeInputException();

        assertValidSquareShape(input);
//        assertValidCircleShape(input);
        // Pattern.matches(".*[\\[\\]]+", input)
    }

}
