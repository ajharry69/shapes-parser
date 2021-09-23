package com.xently.parser.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Constants {
    public static boolean assertValidCircleShape(String input) {
        return Pattern.matches("^.*\\([A-Z0-9]+[)].*$", input);
    }

    /**
     * - an opening square bracket must have a closing square bracket
     * - in between opening and closing square brackets, there must only be numbers
     */
    public static boolean assertValidSquareShape(String input) {
        return Pattern.matches("^.*\\[[0-9]+[]].*$", input);
    }

    public static String charsToString(char... chars) {
        StringBuilder sb = new StringBuilder();
        for (char c : chars) sb.append(c);
        return sb.toString();
    }

    @SafeVarargs
    public static <T> List<T> addAll(List<T> tList, T... ts) {
        List<T> list = new ArrayList<>(tList);
        list.addAll(Arrays.asList(ts));
        return list;
    }
}
