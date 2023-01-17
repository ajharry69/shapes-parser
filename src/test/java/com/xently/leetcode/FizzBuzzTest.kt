package com.xently.leetcode

import org.junit.Test

import org.junit.Assert.*

class FizzBuzzTest {

    @Test
    operator fun invoke() {
        assertEquals(FizzBuzz(3), listOf("1", "2", "Fizz"))
        assertEquals(FizzBuzz(5), listOf("1", "2", "Fizz", "4", "Buzz"))
        assertEquals(
            FizzBuzz(15),
            listOf(
                "1",
                "2",
                "Fizz",
                "4",
                "Buzz",
                "Fizz",
                "7",
                "8",
                "Fizz",
                "Buzz",
                "11",
                "Fizz",
                "13",
                "14",
                "FizzBuzz",
            ),
        )
    }
}