package org.example.distributedlrucacheservice.lru.service;

import org.example.distributedlrucacheservice.lru.model.DoublyLinkedList;
import org.example.distributedlrucacheservice.lru.model.Node;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final DoublyLinkedList dll = new DoublyLinkedList();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;

        if (System.currentTimeMillis() > node.expiryTime) {
            dll.remove(node);
            map.remove(key);
            return -1;
        }

        dll.remove(node);
        dll.addFirst(node);
        return node.value;
    }

    public void put(int key, int value, long ttlMillis) {
        long expiryTime = System.currentTimeMillis() + ttlMillis;

        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            node.expiryTime = expiryTime;
            dll.remove(node);
            dll.addFirst(node);
            return;
        }

        if (map.size() == capacity) {
            Node lru = dll.removeLast();
            map.remove(lru.key);
        }

        Node node = new Node(key, value, expiryTime);
        dll.addFirst(node);
        map.put(key, node);
    }

    public void delete(int key) {
        Node node = map.get(key);
        if (node != null) {
            dll.remove(node);
            map.remove(key);
        }
    }
}


