# Distributed LRU Cache Service (Java)

## 📌 Overview

This project is a **Distributed-ready LRU Cache Service** implemented in **Java + Spring Boot**. It supports:

* O(1) get / put operations
* LRU (Least Recently Used) eviction
* TTL (Time-To-Live) per key
* Automatic background expiration
* REST APIs with Swagger UI
* JUnit test coverage

This project is designed to demonstrate **Low Level Design (LLD)**, **concurrency awareness**, and **backend system design** skills expected from a **3–5 year Java backend engineer**.

---

## 🧠 Key Concepts Used

* HashMap + Doubly Linked List (O(1) LRU)
* TTL with expiry timestamps
* Lazy eviction + background cleaner thread
* RESTful API design
* Swagger / OpenAPI documentation
* JUnit testing

---

## 🏗️ High-Level Architecture

```
Client
   ↓
Swagger / REST API
   ↓
CacheController
   ↓
CacheManager
   ↓
LRUCache
 ├── HashMap (key → Node)
 ├── DoublyLinkedList (usage order)
 └── Scheduled Cleaner Thread
```

---

## 📂 Package Structure

```
org.example.distributedlrucacheservice
 ├── DistributedLruCacheServiceApplication
 ├── lru
 │    ├── controller
 │    │    └── CacheController
 │    ├── service
 │    │    ├── CacheManager
 │    │    └── LRUCache
 │    └── model
 │         ├── Node
 │         └── DoublyLinkedList
 └── test
      └── LRUCacheTest
```

---

## 🔑 Features

### 1️⃣ LRU Eviction

* Uses **Doubly Linked List** to maintain access order
* Head → Most Recently Used
* Tail → Least Recently Used
* Eviction occurs when capacity is reached

### 2️⃣ TTL (Time-To-Live)

* Each cache entry has an expiry timestamp
* Entry is considered invalid after expiry

### 3️⃣ Expiry Deletion Strategies

* **Lazy Eviction**: Entry removed during `get()` if expired
* **Auto Deletion**: Background scheduler removes expired entries periodically

### 4️⃣ Thread Awareness

* Uses concurrent data structures
* Designed to be extended with locks for full thread safety

---

## 🌐 REST API Endpoints

| Method | Endpoint                             | Description         |
| ------ | ------------------------------------ | ------------------- |
| PUT    | /cache/{key}?value={v}&ttlMillis={t} | Insert key with TTL |
| GET    | /cache/{key}                         | Get value           |
| DELETE | /cache/{key}                         | Delete key          |

---

## 📖 Swagger UI

Access Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger is used for:

* API discovery
* Manual testing
* Documentation

---

## 🧪 Testing

JUnit tests cover:

* Basic put/get functionality
* LRU eviction logic
* TTL expiry (lazy deletion)
* Automatic deletion via scheduler

Tests are located at:

```
src/test/java/com/cache/lru/LRUCacheTest.java
```

---

## ▶️ How to Run

### 1️⃣ Build

```bash
mvn clean install
```

### 2️⃣ Run

```bash
mvn spring-boot:run
```

### 3️⃣ Test APIs

Open Swagger UI and test cache operations

---

## 🚀 Future Enhancements

* Full thread safety using ReentrantLocks
* Consistent hashing for multi-node distribution
* Redis replication support
* Metrics (hit rate, eviction count)
* Persistence using write-through strategy

---
