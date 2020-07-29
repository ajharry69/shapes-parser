package com.hava.parser.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestConstants {
    @SafeVarargs
    public static <T> List<T> listOf(T... ts) {
        return new ArrayList<>(Arrays.asList(ts));
    }
}
