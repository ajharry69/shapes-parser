package com.hava.parser;

import com.hava.parser.exceptions.InvalidShapeLabelException;
import org.junit.Test;

import static com.hava.parser.utils.TestConstants.listOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CircleTest {

    @Test
    public void circleHasAnOpeningParenthesisAsStartLabelAndAClosingParenthesisAsEndLabel() throws Exception {
        // opening parenthesis = '('
        // closing parenthesis = ')'
        Circle circle = new Circle("125");
        assertEquals('(', circle.getStartLabel());
        assertEquals(')', circle.getEndLabel());
    }

    @Test
    public void circleMustBeUppercase() throws Exception {
        assertThrows(InvalidShapeLabelException.class, () -> new Circle("label"));
        assertThrows(InvalidShapeLabelException.class, () -> new Circle("Label"));
        assertThrows(InvalidShapeLabelException.class, () -> new Circle("LaBeL"));

        assertEquals("LABEL", new Circle("LABEL").getLabel());
    }

    @Test
    public void circleContainingOtherCirclesOrSquaresIsValid() throws Exception {
        assertEquals('(', new Circle("LABEL", listOf(new Circle("LABEL"), new Circle("LABEL1", listOf(new Square("123"))))).getStartLabel());
    }
}
