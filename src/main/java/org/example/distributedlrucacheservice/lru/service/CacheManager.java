package org.example.distributedlrucacheservice.lru.service;

import org.springframework.stereotype.Component;

@Component
public class CacheManager {

    private final LRUCache cache = new LRUCache(3);

    public int get(int key) {
        return cache.get(key);
    }

    public void put(int key, int value, long ttlMillis) {
        cache.put(key, value, ttlMillis);
    }

    public void delete(int key) {
        cache.delete(key);
    }
}

