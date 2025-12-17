package org.example.distributedlrucacheservice.lru.model;


public class DoublyLinkedList {

    public Node head;
    public Node tail;

    public DoublyLinkedList() {
        head = new Node(-1, -1, Long.MAX_VALUE); // dummy head
        tail = new Node(-1, -1, Long.MAX_VALUE); // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    // Add node right after head (Most Recently Used)
    public void addFirst(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Remove any node
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Remove Least Recently Used node (before tail)
    public Node removeLast() {
        if (tail.prev == head) return null;
        Node lru = tail.prev;
        remove(lru);
        return lru;
    }
}
