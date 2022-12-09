package com.xently.parser.utils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LeetCodeTest {
    @Test
    public void testCarPackingRoof() {
        assertEquals(6L, LeetCode.carPackingRoof(Arrays.asList(6L, 2L, 12L, 7L), 3));
        assertEquals(9L, LeetCode.carPackingRoof(Arrays.asList(2L, 10L, 8L, 17L), 3));
    }
}