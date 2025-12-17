package org.example.distributedlrucacheservice.lru.model;


public class Node {
    public int key;
    public int value;
    public long expiryTime;
    public Node prev, next;

    public Node(int key, int value, long expiryTime) {
        this.key = key;
        this.value = value;
        this.expiryTime = expiryTime;
    }
}


