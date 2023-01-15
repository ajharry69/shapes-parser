package com.xently.leetcode

import com.xently.leetcode.MergeIntervals.sorted
import org.junit.Assert.assertEquals
import org.junit.Test

class MergeIntervalsTest {
    @Test
    fun getSortedInput() {
        assertEquals(arrayOf(1 to 4, 2 to 6).sorted(), arrayOf(1 to 4, 2 to 6))
        assertEquals(arrayOf(2 to 6, 1 to 4).sorted(), arrayOf(1 to 4, 2 to 6))
    }

    @Test
    fun invoke() {
        assertEquals(MergeIntervals(arrayOf(1 to 4, 2 to 6)), arrayOf(1 to 6))
        assertEquals(MergeIntervals(arrayOf(1 to 4, 2 to 6, 6 to 10)), arrayOf(1 to 10))
        assertEquals(MergeIntervals(arrayOf(1 to 4, 2 to 6, 7 to 10)), arrayOf(1 to 6, 7 to 10))

        assertEquals(MergeIntervals(arrayOf(1 to 3, 2 to 6, 8 to 10, 15 to 18)), arrayOf(1 to 6, 8 to 10, 15 to 18))
        assertEquals(MergeIntervals(arrayOf(1 to 4, 4 to 5)), arrayOf(1 to 5))
    }
}