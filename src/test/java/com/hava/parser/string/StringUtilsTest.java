package com.hava.parser.string;

import org.junit.Test;

import static com.hava.parser.string.StringUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringUtilsTest {
    @Test
    public void testTrimStart() {
        assertNull(trimStart(null));
        assertEquals("", trimStart(""));
        assertEquals("", trimStart(" "));
        assertEquals("", trimStart("       "));
        assertEquals("a", trimStart("      a"));
        assertEquals("a ", trimStart("      a "));
        assertEquals("a b", trimStart("      a b"));
        assertEquals("ab cd ", trimStart("      ab cd "));
    }

    @Test
    public void testTrimEnd() {
        assertNull(trimEnd(null));
        assertEquals("", trimEnd(""));
        assertEquals("", trimEnd(" "));
        assertEquals("", trimEnd("       "));
        assertEquals(" a", trimEnd(" a"));
        assertEquals(" a", trimEnd(" a "));
        assertEquals("ab", trimEnd("ab  "));
        assertEquals("ab cd", trimEnd("ab cd   "));
    }

    @Test
    public void testTrim() {
        assertNull(trim(null));
        assertEquals("", trim(""));
        assertEquals("", trim(" "));
        assertEquals("", trim("       "));
        assertEquals("abc", trim("abc"));
        assertEquals("ab cd", trim("ab cd"));
        assertEquals("abc", trim("  abc  "));
        assertEquals("ab cd", trim("  ab cd  "));
    }

    @Test
    public void test_timeComplexity_trimUtil() {
        String str = "        hello world!   Coding is great!     ";
        long st = System.currentTimeMillis();
        System.out.println("<<" + trim(str) + ">>");
        long et = System.currentTimeMillis();
        System.out.format("Time Taken(trimUtil): %d%n", et - st);
    }

    @Test
    public void test_timeComplexity_trim() {
        String str = "        hello world!   Coding is great!     ";
        long st = System.currentTimeMillis();
        System.out.println("<<" + str.trim() + ">>");
        long et = System.currentTimeMillis();
        System.out.format("Time Taken(trim): %d%n", et - st);
    }
}
