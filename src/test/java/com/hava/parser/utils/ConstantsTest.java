package com.hava.parser.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        assertFalse(Constants.assertValidCircleShape("()"));
        assertFalse(Constants.assertValidCircleShape("([)"));
        assertFalse(Constants.assertValidCircleShape("(()"));
        assertFalse(Constants.assertValidCircleShape("())"));
    }

    @Test
    public void assertInvalidSquareShape() {
        // an opening square bracket must have a closing square bracket
        assertFalse(Constants.assertValidSquareShape("[13)"));
        assertFalse(Constants.assertValidSquareShape("([13)"));
        assertFalse(Constants.assertValidSquareShape("([13)]"));
        assertFalse(Constants.assertValidSquareShape("]([13)]"));

        // in between opening and closing square brackets, there must only be numbers
        assertFalse(Constants.assertValidSquareShape("[]"));
        assertFalse(Constants.assertValidSquareShape("[ ]"));
        assertFalse(Constants.assertValidSquareShape("[2D]"));
        assertFalse(Constants.assertValidSquareShape("[E2D]"));
    }
}
