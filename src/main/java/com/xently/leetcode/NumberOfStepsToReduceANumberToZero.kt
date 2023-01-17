package com.xently.leetcode

object NumberOfStepsToReduceANumberToZero {
    operator fun invoke(num: Int): Int {
        /*var count = 0
        var n = num
        while (n > 0) {
            if (n % 2 == 0) {
                n /= 2
            } else {
                n -= 1
            }
            count += 1
        }
        return count*/
        var count = 0
        var n = num
        while (n > 0) {
            if ((n and 1) == 0) {
                n = n shr 1
            } else {
                n -= 1
            }
            count += 1
        }
        return count
    }
}