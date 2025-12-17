package org.example.distributedlrucacheservice.lru.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.distributedlrucacheservice.lru.service.CacheManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
@Tag(name = "LRU Cache API", description = "Distributed LRU Cache with TTL")
public class CacheController {

    private final CacheManager cacheManager;

    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Operation(summary = "Put key-value with TTL")
    @PutMapping("/{key}")
    public String put(
            @PathVariable int key,
            @RequestParam int value,
            @RequestParam long ttlMillis) {

        cacheManager.put(key, value, ttlMillis);
        return "Inserted key=" + key;
    }

    @Operation(summary = "Get value by key")
    @GetMapping("/{key}")
    public int get(@PathVariable int key) {
        return cacheManager.get(key);
    }

    @Operation(summary = "Delete key from cache")
    @DeleteMapping("/{key}")
    public String delete(@PathVariable int key) {
        cacheManager.delete(key);
        return "Deleted key=" + key;
    }
}

