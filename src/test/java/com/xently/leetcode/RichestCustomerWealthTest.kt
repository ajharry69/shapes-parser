package com.xently.leetcode

import org.junit.Test

import org.junit.Assert.*

class RichestCustomerWealthTest {

    @Test
    operator fun invoke() {
        assertEquals(RichestCustomerWealth(arrayOf(intArrayOf(1, 2, 3), intArrayOf(3, 2, 1))), 6)
        assertEquals(RichestCustomerWealth(arrayOf(intArrayOf(1, 5), intArrayOf(7, 3), intArrayOf(3, 5))), 10)
        assertEquals(RichestCustomerWealth(arrayOf(intArrayOf(2, 8, 7), intArrayOf(7, 1, 3), intArrayOf(1, 9, 5))), 17)
    }
}