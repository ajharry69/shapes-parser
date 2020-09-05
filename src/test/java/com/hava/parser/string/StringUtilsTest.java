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
    public void testTrimStartAndEnd() {
        assertNull(trimStartAndEnd(null));
        assertEquals("", trimStartAndEnd(""));
        assertEquals("", trimStartAndEnd(" "));
        assertEquals("", trimStartAndEnd("       "));
        assertEquals("abc", trimStartAndEnd("abc"));
        assertEquals("ab cd", trimStartAndEnd("ab cd"));
        assertEquals("abc", trimStartAndEnd("  abc  "));
        assertEquals("ab cd", trimStartAndEnd("  ab cd  "));
    }

    @Test
    public void test_timeComplexity_trimStartAndEnd() {
        String str = "        hello world!   Coding is great!     ";
        long st = System.currentTimeMillis();
        System.out.println("<<" + trimStartAndEnd(str) + ">>");
        long et = System.currentTimeMillis();
        System.out.format("Time Taken(trimStartAndEnd): %d%n", et - st);
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
