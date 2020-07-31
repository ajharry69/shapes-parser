package com.hava.parser;

import com.hava.parser.exceptions.InvalidInnerShapeException;
import com.hava.parser.exceptions.InvalidShapeInputException;
import com.hava.parser.exceptions.InvalidShapeLabelException;
import com.hava.parser.exceptions.MalformedShapeInputException;

import java.util.*;
import java.util.regex.Pattern;

public class Square extends Shape {
    public Square(String label) throws Exception {
        this(label, Collections.emptyList());
    }

    public Square(String label, List<Shape> innerShapes) throws Exception {
        this(label, innerShapes, Math.abs(UUID.randomUUID().hashCode()));
    }

    public Square(String label, List<Shape> innerShapes, int uid) throws Exception {
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
    public static int createFromInput(String input, List<Shape> containerShapes, int containerCurrentBaseIndex) throws Exception {
        if (input == null || input.isEmpty()) throw new InvalidShapeInputException();

        Stack<Shape> incompleteTraversals = new Stack<>();
        List<Shape> tempInnerShapes = new ArrayList<>();
        List<Shape> shapes = new ArrayList<>();
        int permittedShapesSize = 1; // i.e. shapes here refers to above
        StringBuilder labelBuilder = new StringBuilder();

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            containerCurrentBaseIndex++;
            char c = chars[i];
//            System.out.println(c); // TODO...
            String label = labelBuilder.toString();
            if (c == '[') {
                // square shape initiator(start label)
                if (label.isEmpty()) {
                    if (i - 1 > -1 && chars[i - 1] == '[') {
                        String message = "next square cannot be created while the previous has no label";
                        throw new InvalidInnerShapeException(message); // i.e. [[ is invalid
                    }
                } else {
                    // add new Square to shapes map
                    Square sq = new Square(label);
                    // at this point, input was probably like [12[... hence sq should be the square labelled by 12
                    // add it(sq) to the stack since it could have inner shape(s) one of which should be built from
                    // the current index(i)
                    if (incompleteTraversals.isEmpty() && chars[i - 1] != ']') {
                        // for input [12[34]], below will executed at the 4th element ('[')
                        shapes.add(sq);
                    }
                    if (chars[i - 1] != ']') {
                        // make sure it is ready to accept potential inner shapes for the shape(sp) currently being
                        // pushed to the stack of incomplete traversals
                        assert tempInnerShapes.isEmpty();
                        // at this point, input was probably like [12[... hence sq should be the square labelled by
                        // 12 add it(sq) to the stack since it could have inner shape(s) one of which should be
                        // built from the current index(i)
                        incompleteTraversals.push(sq);
                    }
                }
                // make sure label builder has no labels when a start Square symbol is found; every square must
                // have their own label
                labelBuilder = new StringBuilder();
            } else if (c == ']') {
                // square shape terminator(end label)
                // would return true for the seventh element in [12[34]] and false for the same element in [12[34][56]]
                boolean popIncompleteOrBreakLoop = i + 1 < chars.length && chars[i + 1] != '[';
                if (!incompleteTraversals.empty()) {
                    Shape insq = popIncompleteOrBreakLoop ? incompleteTraversals.pop() : incompleteTraversals.peek();
                    if (!label.isEmpty()) insq.addInnerShapes(new Square(label));

                    if (incompleteTraversals.empty()) {
                        insq.addInnerShapes(tempInnerShapes.toArray(new Shape[0]));
                        tempInnerShapes.clear();
                    } else {
                        if (popIncompleteOrBreakLoop) {
                            insq.addInnerShapes(tempInnerShapes.toArray(new Shape[0]));
                            tempInnerShapes.clear();
                            tempInnerShapes.add(insq);
                        }
                    }
                } else {
                    shapes.add(new Square(label)); // executed when input is [12]
                }
                // reset/delete past labels upon reaching a shape's end label
                labelBuilder = new StringBuilder();
//                System.out.printf("chars[%d] = %s, Shapes Size: %d, Stack Size: %d%n", i, c, shapes.size(), incompleteTraversals.size()); // TODO: ....
                // will be executed when a closing partner for initial opening square bracket has been reached. i.e.
                // char[i] == ']' and it meets the preceding condition. Below are example of when execution will happen:
                //  1. [12], [12[34]] or [12[34[56]]] // happens at last char
                //  2. [12[34[56]]]] // happens at second last char
                if (incompleteTraversals.empty()) {
                    // if the above condition is not met i.e. this comment is reached, the [shapes] should be allowed
                    // to have at most 1 + {the number of time this will run} square bracket
                    permittedShapesSize++;
                    if (popIncompleteOrBreakLoop) break;
                }
            } else if (String.valueOf(c).matches("[0-9]")) {
                // correct label for a Square, append it to labelBuilder
                labelBuilder.append(c);
            } else throw new MalformedShapeInputException();
        }
        System.out.printf("=====================================%n%s%n=====================================%n", shapes); // TODO
        assert shapes.size() <= permittedShapesSize;
        containerShapes.addAll(shapes);
        return containerCurrentBaseIndex;
    }
}
