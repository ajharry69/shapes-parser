package com.xently.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class SinglyLinkedListTest {

    @Test
    public void testAdd() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
//        assertEquals(3, list.printList());
        list.printList();
    }

    @Test
    public void testContains() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.add("apple");
        list.add("banana");
        list.add("orange");
        assertTrue(list.contains("apple"));
        assertFalse(list.contains("mango"));
        list.printList();
    }

    @Test
    public void testRemove() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.remove(20);
//        assertEquals(2, list.printList());
        list.printList();
    }
}
