package com.hava.parser;

import com.hava.parser.exceptions.InvalidInnerShapeException;
import com.hava.parser.exceptions.InvalidShapeLabelException;
import com.hava.parser.exceptions.MalformedShapeInputException;
import com.hava.parser.utils.Response;

import java.util.*;
import java.util.regex.Pattern;

public class Circle extends Shape {
    public Circle(String label) throws RuntimeException {
        this(label, Collections.emptyList());
    }

    public Circle(String label, List<Shape> innerShapes) throws RuntimeException {
        this(label, innerShapes, Math.abs(UUID.randomUUID().hashCode()));
    }

    public Circle(String label, List<Shape> innerShapes, int uid) throws RuntimeException {
        super(label, '(', ')', innerShapes, uid);
        if (!Pattern.matches("[A-Z0-9]+", label))
            throw new InvalidShapeLabelException("Only UPPERCASE letters are supported as label for Circles");
    }

    public static Response fromInput(String input) throws RuntimeException {
        // shapes with probable inner shapes
        Stack<Shape> pendingTraversals = new Stack<>();
        List<Shape> tempInnerShapes = new ArrayList<>();
        List<Shape> shapes = new ArrayList<>();
        StringBuilder labelBuilder = new StringBuilder();
        int traversedCharsCount = 0;
        int permittedShapesCount = 1; // i.e. shapes here refers to above

        char[] chars = input.toCharArray();
        int i = 0;
        while (i < chars.length) {
            traversedCharsCount = i;
            char c = chars[i];
            String label = labelBuilder.toString();
            if (c == '(') {
                onCircleStartLabel(pendingTraversals, tempInnerShapes, shapes, chars, i, label);
                // make sure label builder has no labels when a start Circle symbol is found;
                // every square must have their own label
                labelBuilder = new StringBuilder();
            } else if (c == ')') {
                // would return true for the seventh element in (12(34)) and false for the same
                // element in (12(34](56))
                boolean popPendingTraversalsOrBreakLoop = (i + 1 < chars.length && chars[i + 1] != '(') || i + 1 == chars.length;
                if (pendingTraversals.empty()) {
                    shapes.add(new Circle(label)); // executed when input is (12)
                } else {
                    Shape sq = popPendingTraversalsOrBreakLoop ? pendingTraversals.pop() : pendingTraversals.peek();
                    if (!label.isEmpty()) sq.addInnerShapes(new Circle(label));

                    sq.addInnerShapes(tempInnerShapes, pendingTraversals.empty(), popPendingTraversalsOrBreakLoop);
                }
                // clear any existing labels from the builder upon reaching a shape's end label
                labelBuilder = new StringBuilder();
                // will be executed when a closing "partner" for an initial opening square bracket
                // has been reached. i.e. char[i] == ')' and it meets the preceding condition.
                // Below are example of when execution will happen:
                //  1. (12), (12(34)) or (12(34(56))) // happens at last char
                //  2. (12(34(56)))) // happens at second last char
                if (pendingTraversals.empty()) {
                    // if the above condition is not met i.e. this comment is reached, shapes
                    // returned as part of the response should have at most 1 + {the number of
                    // time this will run} square bracket
                    ++permittedShapesCount;
                    if (popPendingTraversalsOrBreakLoop) break;
                }
            } else if (String.valueOf(c).matches("[A-Z0-9]")) {
                // correct label for a Circle, append it to labelBuilder
                labelBuilder.append(c);
            } else if (c == '[') {
                onCircleStartLabel(pendingTraversals, tempInnerShapes, shapes, chars, i, label);
                // clear any existing labels from the builder for every probable new Square shape
                labelBuilder = new StringBuilder();
                Response response = Square.fromInput(input.substring(i));
                tempInnerShapes.addAll(response.getShapes());
                i += response.getTraversedCharsCount();
                continue;
            } else {
                if (c != ']') throw new MalformedShapeInputException();
            }
            i++;
        }
        assert shapes.size() <= permittedShapesCount;
        return new Response(shapes, traversedCharsCount);
    }

    private static void onCircleStartLabel(Stack<Shape> incompleteTraversals, List<Shape> tempInnerShapes, List<Shape> shapes, char[] chars, int i, String label) throws RuntimeException {
        if (label.isEmpty()) {
            if (i - 1 > -1 && chars[i - 1] == '(') {
                String message = "next square cannot be created while the previous has no label";
                throw new InvalidInnerShapeException(message); // i.e. (( is invalid
            }
        } else {
            // add new Circle to shapes map
            // at this point, input was probably like (12(... hence sq should be the square labelled by 12
            // add it(sq) to the stack since it could have inner shape(s) one of which should be built from
            // the current index(i)
            if (chars[i - 1] != ')') {
                Shape shape = new Circle(label);
                if (incompleteTraversals.isEmpty()) {
                    // for input (12(34)), below will executed at the 4th element ('(')
                    shapes.add(shape);
                }
                // make sure it is ready to accept potential inner shapes for the shape(sp) currently being
                // pushed to the stack of incomplete traversals
                assert tempInnerShapes.isEmpty();
                // at this point, input was probably like (12(... hence shape should be the square labelled by
                // 12 add it(shape) to the stack since it could have inner shape(s) one of which should be
                // built from the current index(i)
                incompleteTraversals.push(shape);
            }
        }
    }
}
