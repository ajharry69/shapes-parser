package com.xently.leetcode

import org.junit.Test

import org.junit.Assert.*

class RunningSumOf1DArrayTest {

    @Test
    operator fun invoke() {
        assertEquals(RunningSumOf1DArray(intArrayOf(1, 1, 1, 1)).toList(), intArrayOf(1, 2, 3, 4).toList())
        assertEquals(RunningSumOf1DArray(intArrayOf(1, 2, 3, 4)).toList(), intArrayOf(1, 3, 6, 10).toList())
        assertEquals(RunningSumOf1DArray(intArrayOf(3, 1, 2, 10, 1)).toList(), intArrayOf(3, 4, 6, 16, 17).toList())
    }
}