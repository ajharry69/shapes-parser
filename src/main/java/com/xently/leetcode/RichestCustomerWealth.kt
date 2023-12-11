package com.xently.leetcode

object RichestCustomerWealth {
    operator fun invoke(accounts: Array<IntArray>): Int {
        /*var wealthOfRichest = 0
        for (account in accounts) {
            var wealth = account[0]
            for (j in 1 until account.size) {
                wealth += account[j]
            }
            if (wealthOfRichest < wealth) {
                wealthOfRichest = wealth
            }
        }
        return wealthOfRichest*/
        return accounts.maxOf(IntArray::sum)
    }
}