package com.xently.parser;

import com.xently.parser.exceptions.InvalidShapeLabelException;
import com.xently.parser.utils.TestConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CircleTest {

    @Test
    public void circleHasAnOpeningParenthesisAsStartLabelAndAClosingParenthesisAsEndLabel() throws RuntimeException {
        // opening parenthesis = '('
        // closing parenthesis = ')'
        Circle circle = new Circle("125");
        assertEquals('(', circle.getStartLabel());
        assertEquals(')', circle.getEndLabel());
    }

    @Test
    public void circleMustBeUppercase() throws RuntimeException {
        assertThrows(InvalidShapeLabelException.class, () -> new Circle("label"));
        assertThrows(InvalidShapeLabelException.class, () -> new Circle("Label"));
        assertThrows(InvalidShapeLabelException.class, () -> new Circle("LaBeL"));

        assertEquals("LABEL", new Circle("LABEL").getLabel());
    }

    @Test
    public void circleContainingOtherCirclesOrSquaresIsValid() throws RuntimeException {
        assertEquals('(', new Circle("LABEL", TestConstants.listOf(new Circle("LABEL"), new Circle("LABEL1", TestConstants.listOf(new Square("123"))))).getStartLabel());
    }
}
