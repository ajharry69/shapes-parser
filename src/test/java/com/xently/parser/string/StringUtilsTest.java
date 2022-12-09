package com.xently.parser.string;

import org.junit.Test;

import static com.xently.parser.string.StringUtils.*;
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
}
