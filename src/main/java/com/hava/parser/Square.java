package com.hava.parser;

import com.hava.parser.exceptions.InvalidInnerShapeException;
import com.hava.parser.exceptions.InvalidShapeLabelException;
import com.hava.parser.exceptions.MalformedShapeInputException;
import com.hava.parser.utils.Response;

import java.util.*;
import java.util.regex.Pattern;

public class Square extends Shape {
    public Square(String label) throws RuntimeException {
        this(label, Collections.emptyList());
    }

    public Square(String label, List<Shape> innerShapes) throws RuntimeException {
        this(label, innerShapes, Math.abs(UUID.randomUUID().hashCode()));
    }

    public Square(String label, List<Shape> innerShapes, int uid) throws RuntimeException {
        super(label, '[', ']', innerShapes, uid);
        if (!(Pattern.matches("^[0-9]+$", label)))
            throw new InvalidShapeLabelException("Square can only be labeled with a number");
        for (Shape shape : innerShapes) {
            if (!(shape instanceof Square))
                throw new InvalidInnerShapeException("Square can only contain other squares");
        }
    }

    /**
     * returns the new index from which container loop is to start traversing
     * input is expected in the format:
     * 1. [\[0-9\]+], [\[0-9\]+[\[0-9\]+]] or [\[0-9\]+[\[0-9\]+]][\[0-9\]+] with optional
     * other appended characters after every closing bracket...
     */
    public static Response fromInput(String input) throws RuntimeException {
        // shapes with probable inner shapes
        Stack<Shape> pendingTraversals = new Stack<>();
        List<Shape> tempInnerShapes = new ArrayList<>();
        List<Shape> shapes = new ArrayList<>();
        StringBuilder labelBuilder = new StringBuilder();
        int permittedShapesCount = 1; // i.e. shapes here refers to above
        int traversedCharsCount = 0;

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            traversedCharsCount++;
            char c = chars[i];
            String label = labelBuilder.toString();
            if (c == '[') {
                if (label.isEmpty()) {
                    if (i - 1 > -1 && chars[i - 1] == '[') {
                        // Invalidate [[...
                        String message = "next square cannot be created while the previous has no label";
                        throw new InvalidInnerShapeException(message);
                    }
                } else {
                    if (chars[i - 1] != ']') {
                        Shape shape = new Square(label);
                        if (pendingTraversals.isEmpty()) {
                            // for input [12[34]], below will executed at the 4th element ('[')
                            shapes.add(shape);
                        }
                        // assert readiness of temporary inner shapes to accept potential inner shapes
                        // of the shape create above.
                        // The inner shapes will be added to it when a square shape end label ']' is
                        // reached later on.
                        assert tempInnerShapes.isEmpty();
                        // at this point, input was probably like [12[... hence shape should be of type
                        // square labelled by 12.
                        // Add it(shape) to the stack since it could have inner shape(s) one of which
                        // should be built from the current index(i).
                        pendingTraversals.push(shape);
                    }
                }
                // clear any existing labels from the builder for every probable new Square shape
                labelBuilder = new StringBuilder();
            } else if (c == ']') {
                // would return true for the seventh element in [12[34]] and false for the same
                // element in [12[34][56]]
                boolean popPendingTraversalsOrBreakLoop = i + 1 < chars.length && chars[i + 1] != '[';
                if (pendingTraversals.empty()) {
                    shapes.add(new Square(label)); // executed when input is [12]
                } else {
                    Shape shape = popPendingTraversalsOrBreakLoop ? pendingTraversals.pop() : pendingTraversals.peek();
                    if (!label.isEmpty()) shape.addInnerShapes(new Square(label));

                    shape.addInnerShapes(tempInnerShapes, pendingTraversals.empty(), popPendingTraversalsOrBreakLoop);
                }
                // clear any existing labels from the builder upon reaching a shape's end label
                labelBuilder = new StringBuilder();
                // will be executed when a closing "partner" for an initial opening square bracket
                // has been reached. i.e. char[i] == ']' and it meets the preceding condition.
                // Below are example of when execution will happen:
                //  1. [12], [12[34]] or [12[34[56]]] // happens at last char
                //  2. [12[34[56]]]] // happens at second last char
                if (pendingTraversals.empty()) {
                    // if the above condition is not met i.e. this comment is reached, shapes
                    // returned as part of the response should have at most 1 + {the number of
                    // time this will run} square bracket
                    ++permittedShapesCount;
                    if (popPendingTraversalsOrBreakLoop) break;
                }
            } else if (String.valueOf(c).matches("[0-9]")) {
                // correct label for a Square, append it to the builder
                labelBuilder.append(c);
            } else throw new MalformedShapeInputException();
        }
        assert shapes.size() <= permittedShapesCount;
        return new Response(shapes, traversedCharsCount);
    }
}
