package com.hava.parser.utils;

import com.hava.parser.exceptions.MalformedShapeInputException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ConstantsTest {

    @Test
    public void charsToString() {
        assertEquals("", Constants.charsToString());
        assertEquals("abc", Constants.charsToString('a', 'b', 'c'));
        assertEquals("1bc", Constants.charsToString('1', 'b', 'c'));
        assertEquals("\nbc", Constants.charsToString('\n', 'b', 'c'));
    }

    @Test
    public void assertInvalidCircleShape() {
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidCircleShape("()"));
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidCircleShape("([)"));
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidCircleShape("(()"));
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidCircleShape("())"));
    }

    @Test
    public void assertInvalidSquareShape() {
        // an opening square bracket must have a closing square bracket
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidSquareShape("[13)"));
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidSquareShape("([13)"));
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidSquareShape("([13)]"));
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidSquareShape("]([13)]"));

        // in between opening and closing square brackets, there must only be numbers
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidSquareShape("[]"));
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidSquareShape("[ ]"));
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidSquareShape("[2D]"));
        assertThrows(MalformedShapeInputException.class, () -> Constants.assertValidSquareShape("[E2D]"));
    }
}
