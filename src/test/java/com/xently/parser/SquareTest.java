package com.xently.parser;

import com.xently.parser.exceptions.InvalidInnerShapeException;
import com.xently.parser.exceptions.InvalidShapeLabelException;
import com.xently.parser.utils.TestConstants;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SquareTest {
    @Test
    public void squareEquality() throws RuntimeException {
        assertEquals(new Square("12", Collections.emptyList(), 1), new Square("12", Collections.emptyList(), 1));
    }

    @Test
    public void squareHasAnOpeningSquareBracketAsStartLabelAndAClosingSquareBracketAsEndLabel() throws RuntimeException {
        // opening square bracket = '['
        // closing square bracket = ']'
        Square square = new Square("125");
        assertEquals('[', square.getStartLabel());
        assertEquals(']', square.getEndLabel());
    }

    @Test
    public void noneNumberLabelsForASquare_throwsAnInvalidShapeLabelException() throws RuntimeException {
        // example of invalid square labels
        assertThrows(InvalidShapeLabelException.class, () -> new Square("LABEL"));
        // valid square labels
        assertEquals('[', new Square("123").getStartLabel());
        assertEquals(']', new Square("123").getEndLabel());
        assertEquals(']', new Square("123").getEndLabel());
    }

    @Test
    public void squareContainingInnerShapes_thatAreNotOfTypeSquare_throwsInvalidInnerShapeException() throws RuntimeException {
        assertThrows(InvalidInnerShapeException.class, () -> new Square("123", simpleInvalidSquareInnerShapes()));
        assertThrows(InvalidInnerShapeException.class, () -> new Square("123", complexInvalidSquareInnerShapes()));

        Square validSquare1 = new Square("1234", simpleValidSquareInnerShapes());
        assertTrue(validSquare1.getInnerShapes().size() > 0);

        Square validSquare2 = new Square("1234", complexValidSquareInnerShapes());
        assertTrue(validSquare2.getInnerShapes().size() > 0);
    }

    private List<Shape> simpleValidSquareInnerShapes() throws RuntimeException {
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Square("123"));
        shapeList.add(new Square("321"));
        return shapeList;
    }

    private List<Shape> complexValidSquareInnerShapes() throws RuntimeException {
        Square square = new Square("123", TestConstants.listOf(new Square("213")));
        return TestConstants.listOf(square, new Square("321"));
    }

    private List<Shape> simpleInvalidSquareInnerShapes() throws RuntimeException {
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Square("123"));
        shapeList.add(new Circle("LABEL")); // invalid for square
        return shapeList;
    }

    private List<Shape> complexInvalidSquareInnerShapes() throws RuntimeException {
        Square square = new Square("321", TestConstants.listOf(new Square("3215", TestConstants.listOf(new Circle("LABEL")))));
        return TestConstants.listOf(square, new Square("123"), new Square("3215", TestConstants.listOf(new Circle("LABEL"))));
    }
}
