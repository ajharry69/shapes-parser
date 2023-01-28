package com.xently.leetcode

object MiddleOfTheLinkedList {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    operator fun invoke(head: ListNode?): ListNode? {
        var slowPointer=head
        var fastPointer=head

        while(slowPointer != null && fastPointer?.next != null){
            slowPointer= slowPointer.next
            fastPointer= fastPointer.next?.next
        }
        return slowPointer
//        if (head == null) return null
//        if (head.next == null) return head
//
//        var currentNextNode = head.next
//        // So far, the only node that's been counted is the head node - the next node after
//        // the head will be counted within the while loop
//        var sizeOfLinkedList = 1
//        while (currentNextNode != null) {
//            sizeOfLinkedList++
//            currentNextNode = currentNextNode.next
//        }
//
//        val midPointToReturnListNode: Int = (sizeOfLinkedList / 2) + 1
//        currentNextNode = head.next
//        sizeOfLinkedList = 2 // We have already traversed the head and the next after head
//        while (sizeOfLinkedList != midPointToReturnListNode) {
//            sizeOfLinkedList++
//            currentNextNode = currentNextNode?.next
//        }
//        return currentNextNode
    }
}