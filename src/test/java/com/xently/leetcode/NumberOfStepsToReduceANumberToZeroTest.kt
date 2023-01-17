package com.xently.leetcode

import org.junit.Test

import org.junit.Assert.*

class NumberOfStepsToReduceANumberToZeroTest {

    @Test
    operator fun invoke() {
        assertEquals(NumberOfStepsToReduceANumberToZero(14), 6)
        assertEquals(NumberOfStepsToReduceANumberToZero(8), 4)
        assertEquals(NumberOfStepsToReduceANumberToZero(123), 12)
    }
}