package com.paritosh.lru.service;

public interface CacheService {
    String put(String key, String value);
    String get(String key);
}
