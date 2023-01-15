package com.xently.leetcode

object MergeIntervals {
    fun Array<Pair<Int, Int>>.sorted() = sortedArrayWith { p0, p1 ->
        if (p0.first == p1.first && p0.second == p1.second) {
            0
        } else if (p0.first > p1.first) {
            1
        } else {
            -1
        }
    }

    operator fun invoke(intervals: Array<Pair<Int, Int>>) = buildList<Pair<Int, Int>> {
        var indexOfLastAdded = 0
        for ((index, interval) in intervals.sorted().withIndex()) {
            if (index == 0) {
                add(interval)
            } else {
                val (startOfLastAddedInterval, endOfLastAddedInterval) = this[indexOfLastAdded]
                if (endOfLastAddedInterval >= interval.first) {
                    set(indexOfLastAdded, startOfLastAddedInterval to interval.second)
                } else {
                    add(interval)
                    indexOfLastAdded += 1
                }
            }
        }
    }.toTypedArray()
}