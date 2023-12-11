package com.xently.datastructures;

public class SinglyLinkedList<T> {
    private Node<T> head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.getData() + " -> ");
            current = current.getNext();
        }
        System.out.println("NULL");
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void remove(T data) {
        if (head == null) {
            return;
        }
        if (head.getData().equals(data)) {
            head = head.getNext();
        } else {
            Node<T> current = head;
            Node<T> previous = null;
            while (current != null) {
                if (current.getData().equals(data)) {
                    previous.setNext(current.getNext());
                    break;
                }
                previous = current;
                current = current.getNext();
            }
        }
    }
}
