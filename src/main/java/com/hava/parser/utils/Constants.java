package com.hava.parser.utils;

import com.hava.parser.exceptions.MalformedShapeInputException;

import java.util.regex.Pattern;

public class Constants {
    public static void assertValidCircleShape(String input) throws MalformedShapeInputException {
        boolean validCircle = Pattern.matches("^.*\\([A-Z0-9]+[)].*$", input);
        if (!validCircle) throw new MalformedShapeInputException();
    }

    /**
     * - an opening square bracket must have a closing square bracket
     * - in between opening and closing square brackets, there must only be numbers
     */
    public static void assertValidSquareShape(String input) throws MalformedShapeInputException {
        boolean validSquare = Pattern.matches("^.*\\[[0-9]+[]].*$", input);
        if (!validSquare) throw new MalformedShapeInputException();
    }

    public static String charsToString(char... chars) {
        StringBuilder sb = new StringBuilder();
        for (char c : chars) sb.append(c);
        return sb.toString();
    }
}
