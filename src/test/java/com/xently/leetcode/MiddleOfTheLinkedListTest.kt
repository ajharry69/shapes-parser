package com.xently.leetcode

import com.xently.leetcode.MiddleOfTheLinkedList.ListNode
import org.junit.Test

import org.junit.Assert.*

class MiddleOfTheLinkedListTest {

    @Test
    fun `invoke for null head`() {
        assertNull(MiddleOfTheLinkedList(null))
    }

    @Test
    fun `invoke for single list node`() {
        assertEquals(1, MiddleOfTheLinkedList(ListNode(1))!!.`val`)
    }

    @Test
    fun `invoke for oddly sized list nodes`() {
        assertEquals(
            6,
            MiddleOfTheLinkedList(ListNode(1).apply {
                next = ListNode(2).apply {
                    next = ListNode(3).apply {
                        next = ListNode(4).apply {
                            next = ListNode(5).apply {
                                next = ListNode(6).apply {
                                    next = ListNode(7).apply {
                                        next= ListNode(8).apply {
                                            next = ListNode(9).apply {
                                                next = ListNode(10).apply {
                                                    next = ListNode(11).apply {

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            })!!.`val`,
        )
    }

    @Test
    fun `invoke for evenly sized list nodes`() {
        assertEquals(
            4,
            MiddleOfTheLinkedList(ListNode(1).apply {
                next = ListNode(2).apply {
                    next = ListNode(3).apply {
                        next = ListNode(4).apply {
                            next = ListNode(5).apply {
                                next = ListNode(6)
                            }
                        }
                    }
                }
            })!!.`val`,
        )
    }
}