package com.hava.parser;

import com.hava.parser.exceptions.InvalidInnerShapeException;
import com.hava.parser.exceptions.InvalidShapeLabelException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.hava.parser.utils.TestConstants.listOf;
import static org.junit.Assert.*;

public class SquareTest {
    @Test
    public void squareEquality() throws Exception {
        assertEquals(new Square("12", Collections.emptyList(), 1), new Square("12", Collections.emptyList(), 1));
    }

    @Test
    public void squareHasAnOpeningSquareBracketAsStartLabelAndAClosingSquareBracketAsEndLabel() throws Exception {
        // opening square bracket = '['
        // closing square bracket = ']'
        Square square = new Square("125");
        assertEquals('[', square.getStartLabel());
        assertEquals(']', square.getEndLabel());
    }

    @Test
    public void noneNumberLabelsForASquare_throwsAnInvalidShapeLabelException() throws Exception {
        // example of invalid square labels
        assertThrows(InvalidShapeLabelException.class, () -> new Square("LABEL"));
        // valid square labels
        assertEquals('[', new Square("123").getStartLabel());
        assertEquals(']', new Square("123").getEndLabel());
        assertEquals(']', new Square("123").getEndLabel());
    }

    @Test
    public void squareContainingInnerShapes_thatAreNotOfTypeSquare_throwsInvalidInnerShapeException() throws Exception {
        assertThrows(InvalidInnerShapeException.class, () -> new Square("123", simpleInvalidSquareInnerShapes()));
        assertThrows(InvalidInnerShapeException.class, () -> new Square("123", complexInvalidSquareInnerShapes()));

        Square validSquare1 = new Square("1234", simpleValidSquareInnerShapes());
        assertTrue(validSquare1.getInnerShapes().size() > 0);

        Square validSquare2 = new Square("1234", complexValidSquareInnerShapes());
        assertTrue(validSquare2.getInnerShapes().size() > 0);
    }

    private List<Shape> simpleValidSquareInnerShapes() throws Exception {
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Square("123"));
        shapeList.add(new Square("321"));
        return shapeList;
    }

    private List<Shape> complexValidSquareInnerShapes() throws Exception {
        Square square = new Square("123", listOf(new Square("213")));
        return listOf(square, new Square("321"));
    }

    private List<Shape> simpleInvalidSquareInnerShapes() throws Exception {
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Square("123"));
        shapeList.add(new Circle("LABEL")); // invalid for square
        return shapeList;
    }

    private List<Shape> complexInvalidSquareInnerShapes() throws Exception {
        Square square = new Square("321", listOf(new Square("3215", listOf(new Circle("LABEL")))));
        return listOf(square, new Square("123"), new Square("3215", listOf(new Circle("LABEL"))));
    }
}
