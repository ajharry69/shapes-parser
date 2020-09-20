package com.hava.parser.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static com.hava.parser.utils.TechSavanna.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TechSavannaTest {
    @Test
    public void getCountTest() {
        assertEquals(1, getCount("test", "test"));
        assertEquals(2, getCount("testtest", "test"));
        assertEquals(3, getCount("testtesttest", "test"));
        assertEquals(4, getCount("testtesttest test", "test"));
        assertEquals(1, getCount("spaced test", "spaced test"));
        assertEquals(2, getCount("spaced testspaced test", "spaced test"));
        assertEquals(0, getCount("", "spaced test"));
        assertEquals(1, getCount("test", "s"));
        assertEquals(0, getCount("test", ""));
    }

    @Test
    public void getTextTest() {
        assertEquals("", getText("\"text\":{}"));
        assertEquals("1", getText("\"text\":{1}"));
        assertEquals("\"", getText("\"text\":{\"}"));
        assertEquals("\"*\":\"a b $\"", getText("\"text\":{\"*\":\"a b $\"}"));
        assertEquals("\"*\":\"a b $\"", getText("{\"text\":{\"*\":\"a b $\"}"));
    }

    @Test
    public void minDiffTest() {
        assertEquals(0, minDiff(Arrays.asList(0)));
        assertEquals(3, minDiff(Arrays.asList(1, 3, 3, 2, 4)));
    }

    @Test
    public void factorialTest() {
        assertEquals(120, factorial(5));
    }

    @Test
    public void maxStrengthTest() {
        assertEquals(16410240, maxStrength(23));
        assertEquals(3636010, maxStrength(5));
    }

    @Test
    public void fizzBuzzTest() {
        assertEquals("1\n2\nFizz\n4\nBuzz\n", fizzBuzz(5));
        assertEquals("1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizzBuzz\n", fizzBuzz(15));
    }

    @Test
    @Ignore("Needs a mock of the wikipedia response server")
    public void getTopicCountTest() throws Exception {
        assertTrue(getTopicCount("pizza") > 0);
        assertTrue(getTopicCount("pasta") > 0);
    }
}
