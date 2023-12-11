package com.xently.codility

import com.xently.codility.Solution.solution
import org.junit.Assert.assertEquals
import org.junit.Test

class SolutionTest {
    @Test
    fun `test case 1 solution`() {
        assertEquals(
            6,
            solution(intArrayOf(4, 1, 2, 3))
        )
    }

    @Test
    fun `test case 2 solution`() {
        assertEquals(
            7,
            solution(intArrayOf(1, 2, 3, 3, 2, 1, 5))
        )
    }

    @Test
    fun `test case 3 solution`() {
        println(2_999_999_998 % 1_000_000_000)
        assertEquals(
            999_999_998,
            solution(
                intArrayOf(
                    1_000_000_000,
                    1,
                    2,
                    2,
                    1_000_000_000,
                    1,
                    1_000_000_000,
                )
            )
        )
    }
}