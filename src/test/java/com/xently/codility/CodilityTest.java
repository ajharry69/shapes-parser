package com.xently.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodilityTest {
    @Test
    public void largestAndSmallestPossibleValues() {
        System.out.println("Smallest INT: " + Integer.MIN_VALUE);
        System.out.println("Largest INT: " + Integer.MAX_VALUE);
        System.out.println("==============================");
        System.out.println("Smallest LONG: " + Long.MIN_VALUE);
        System.out.println("Largest LONG: " + Long.MAX_VALUE);
        System.out.println("==============================");
        System.out.println("Smallest Float: " + Float.MIN_VALUE);
        System.out.println("Largest Float: " + Float.MAX_VALUE);
        System.out.println("==============================");
        System.out.println("Smallest Double: " + Double.MIN_VALUE);
        System.out.println("Largest Double: " + Double.MAX_VALUE);
    }

    @Test
    public void test1() {
        assertEquals(4, Codility.solution(new int[]{1, 2, 3}));
        assertEquals(1, Codility.solution(new int[]{-1, -3}));
    }

    @Test
    public void test2() {
        assertEquals(1, Codility.solution(new int[]{2}));
        assertEquals(2, Codility.solution(new int[]{1}));
        assertEquals(2, Codility.solution(new int[]{1, 3}));
        assertEquals(2, Codility.solution(new int[]{1, 3, 6}));
        assertEquals(2, Codility.solution(new int[]{1, 3, 6, 4}));
        assertEquals(2, Codility.solution(new int[]{1, 3, 6, 4, 1}));
        assertEquals(5, Codility.solution(new int[]{1, 3, 6, 4, 1, 2}));
    }

    @Test
    public void shouldInvert() {
        assertEquals("",
                Codility.invert(null)
        );
        assertEquals("",
                Codility.invert("")
        );
        assertEquals("dcba",
                Codility.invert("abcd")
        );
    }

}