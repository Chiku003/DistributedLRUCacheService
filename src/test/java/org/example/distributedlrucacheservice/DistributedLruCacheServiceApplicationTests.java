package org.example.distributedlrucacheservice;

import org.example.distributedlrucacheservice.lru.service.LRUCache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DistributedLruCacheServiceApplicationTests {

    private LRUCache cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache(2);
    }

    @Test
    void testPutAndGet() {
        cache.put(1, 100, 5000);
        assertEquals(100, cache.get(1));
    }

    @Test
    void testLRUEviction() {
        cache.put(1, 100, 5000);
        cache.put(2, 200, 5000);

        cache.get(1);

        cache.put(3, 300, 5000);

        assertEquals(100, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(300, cache.get(3));
    }

    @Test
    void testTTLExpiry() throws InterruptedException {
        cache.put(1, 100, 1000);

        Thread.sleep(1500);

        assertEquals(-1, cache.get(1)); // expired
    }

    @Test
    void testAutoDelete() throws InterruptedException {
        cache.put(1, 100, 1000);

        Thread.sleep(6000);

        assertEquals(-1, cache.get(1));
    }
}

