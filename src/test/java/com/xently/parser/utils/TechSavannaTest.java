package com.xently.parser.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TechSavannaTest {
    @Test
    public void solutionTest() {
        Assert.assertEquals(4, TechSavanna.solution(new int[]{1, 2, 3, 4, 5, 6}, 5));
        Assert.assertEquals(4, TechSavanna.solution(new int[]{1, 2, 3, 4, 5}, 5));
        Assert.assertEquals(3, TechSavanna.solution(new int[]{1, 2, 3, 4, 5, 6}, 4));
        Assert.assertEquals(3, TechSavanna.solution(new int[]{1, 2, 3, 4, 5}, 4));
    }

    @Test
    public void solution1Test() {
        int[] A = new int[]{1, 3, 2, 1};
        int[] B = new int[]{4, 2, 5, 3, 2};
        Assert.assertEquals(2, TechSavanna.solution1(A, B));
        A = new int[]{2, 1};
        B = new int[]{3, 3};
        Assert.assertEquals(-1, TechSavanna.solution1(A, B));
        A = new int[]{4, 2, 5, 3, 2};
        B = new int[]{1, 3, 2, 1};
        Assert.assertEquals(2, TechSavanna.solution1(A, B));
    }

    @Test
    public void smallestNumberTest() {
        Assert.assertEquals(5, TechSavanna.smallestPositiveInteger(new int[]{1, 3, 6, 4, 1, 2}));
        Assert.assertEquals(4, TechSavanna.smallestPositiveInteger(new int[]{1, 2, 3}));
        Assert.assertEquals(1, TechSavanna.smallestPositiveInteger(new int[]{-1, -3}));
    }

    @Test
    public void getCountTest() {
        Assert.assertEquals(1, TechSavanna.getCount("test", "test"));
        Assert.assertEquals(2, TechSavanna.getCount("testtest", "test"));
        Assert.assertEquals(3, TechSavanna.getCount("testtesttest", "test"));
        Assert.assertEquals(4, TechSavanna.getCount("testtesttest test", "test"));
        Assert.assertEquals(1, TechSavanna.getCount("spaced test", "spaced test"));
        Assert.assertEquals(2, TechSavanna.getCount("spaced testspaced test", "spaced test"));
        Assert.assertEquals(0, TechSavanna.getCount("", "spaced test"));
        Assert.assertEquals(1, TechSavanna.getCount("test", "s"));
        Assert.assertEquals(0, TechSavanna.getCount("test", ""));
    }

    @Test
    public void getTextTest() {
        Assert.assertEquals("", TechSavanna.getText("\"text\":{}"));
        Assert.assertEquals("1", TechSavanna.getText("\"text\":{1}"));
        Assert.assertEquals("\"", TechSavanna.getText("\"text\":{\"}"));
        Assert.assertEquals("\"*\":\"a b $\"", TechSavanna.getText("\"text\":{\"*\":\"a b $\"}"));
        Assert.assertEquals("\"*\":\"a b $\"", TechSavanna.getText("{\"text\":{\"*\":\"a b $\"}"));
    }

    @Test
    public void minDiffTest() {
        Assert.assertEquals(0, TechSavanna.minDiff(Arrays.asList(0)));
        Assert.assertEquals(3, TechSavanna.minDiff(Arrays.asList(1, 3, 3, 2, 4)));
    }

    @Test
    public void factorialTest() {
        Assert.assertEquals(120, TechSavanna.factorial(5));
    }

    @Test
    public void getTruesCount() {
        Assert.assertEquals(0, TechSavanna.getTruesCount(""));
        Assert.assertEquals(0, TechSavanna.getTruesCount("+"));
        Assert.assertEquals(0, TechSavanna.getTruesCount("d"));
        Assert.assertEquals(0, TechSavanna.getTruesCount("+d"));
        Assert.assertEquals(0, TechSavanna.getTruesCount("d+"));
        Assert.assertEquals(1, TechSavanna.getTruesCount("+d+"));
        Assert.assertEquals(1, TechSavanna.getTruesCount("+d++"));
        Assert.assertEquals(1, TechSavanna.getTruesCount("+d++d"));
        Assert.assertEquals(2, TechSavanna.getTruesCount("+d+d+"));
        Assert.assertEquals(2, TechSavanna.getTruesCount("+d++d+"));
    }

    @Test
    public void maxStrengthTest() {
        Assert.assertEquals(16410240, TechSavanna.maxStrength(23));
        Assert.assertEquals(3636010, TechSavanna.maxStrength(5));
    }

    @Test
    public void fizzBuzzTest() {
        Assert.assertEquals("1\n2\nFizz\n4\nBuzz\n", TechSavanna.fizzBuzz(5));
        Assert.assertEquals("1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizzBuzz\n", TechSavanna.fizzBuzz(15));
    }

    @Test
    @Ignore("Needs a mock of the wikipedia response server")
    public void getTopicCountTest() throws Exception {
        assertTrue(TechSavanna.getTopicCount("pizza") > 0);
        assertTrue(TechSavanna.getTopicCount("pasta") > 0);
    }
}
