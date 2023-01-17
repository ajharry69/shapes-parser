package com.xently.leetcode

object RunningSumOf1DArray {
    operator fun invoke(nums: IntArray): IntArray {
        val output = IntArray(nums.size)
        output[0] = nums[0]

        for (i in 1 until nums.size) {
            output[i] = nums[i] + output[i - 1]
        }

        return output
    }
}