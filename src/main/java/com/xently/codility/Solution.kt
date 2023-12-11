package com.xently.codility


object Solution {
    fun solution(A: IntArray): Int {
        var max: Long = 0
        var deletionCount = 0
        val visited = mutableListOf<Int>()
        for (i in A.indices) {
            val lastElement = A[(A.size - 1) - i]
            if (lastElement == 2/* || lastElement in visited*/) {
                deletionCount += 1
                continue
            }
            if (((i - deletionCount) + 1) % 2 == 0) {
                // Even
                max -= lastElement
            } else {
                // Odd
                max += lastElement
            }
            visited.add(lastElement)
        }

        return (max % 1_000_000_000).toInt()
    }
}