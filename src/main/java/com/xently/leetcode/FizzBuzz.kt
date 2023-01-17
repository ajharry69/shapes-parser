package com.xently.leetcode

object FizzBuzz {
    operator fun invoke(n: Int): List<String> {
        val answer = mutableListOf<String>()
        for (i in 1..n) {
            var ans = ""
            if (i % 3 == 0) {
                ans = "Fizz"
            }
            if (i % 5 == 0) {
                ans += "Buzz"
            }
            if (ans.isEmpty()) {
                ans = "$i"
            }

            answer.add(ans)

            /*val isDivisibleBy3 = i % 3 == 0
            val isDivisibleBy5 = i % 5 == 0
            when {
                isDivisibleBy3 && isDivisibleBy5 -> "FizzBuzz"
                isDivisibleBy3 -> "Fizz"
                isDivisibleBy5 -> "Buzz"
                else -> "$i"
            }.let(answer::add)*/
        }

        return answer
    }
}