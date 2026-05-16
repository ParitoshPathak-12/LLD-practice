package com.paritosh.lru.service;

import com.paritosh.lru.common.constants.LRUCacheConstants;
import com.paritosh.lru.model.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheServiceImpl implements CacheService {
    private final int capacity;
    private final DoublyLinkedListNode head;
    private final DoublyLinkedListNode tail;
    Map<String, DoublyLinkedListNode> cache;

    public LRUCacheServiceImpl(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new DoublyLinkedListNode(null, null);
        tail = new DoublyLinkedListNode(null, null);

        /*
        keep recently used nodes near the tail,
        and least recently used nodes near the head.
        */

        head.next = tail;
        head.prev = null; // by default it will be null

        tail.prev = head;
        tail.next = null; // by default it will be null
    }

    /*
    if key exists:
    - find the node
    - remove from list
    - update the value
    - add at the end of the list

    if key does not exist:
    - if cache size == capacity, remove the LRU node (node at front near the head), from cache also
    - create the new node
    - insert at the end of the list
    - add into cache
    */
    @Override
    public String put(String key, String value) {
        if (cache.containsKey(key)) {
            DoublyLinkedListNode node = cache.get(key);
            removeNode(node);
            node.value = value;
            addToEnd(node);
            return LRUCacheConstants.KEY_EXISTS_UPDATED;
        } else {
            if (cache.size() == capacity) {
                DoublyLinkedListNode lruNode = head.next;
                removeNode(lruNode);
                cache.remove(lruNode.key);
            }

            DoublyLinkedListNode node = new DoublyLinkedListNode(key, value);
            addToEnd(node);
            cache.put(key, node);
            return LRUCacheConstants.KEY_VALUE_ADDED;
        }
    }

    /*
    If key exists:
    - get the node from cache
    - remove it from the list
    - add at the end of the list
    */
    @Override
    public String get(String key) {
        if (!cache.containsKey(key)) {
            return LRUCacheConstants.KEY_NOT_FOUND;
        } else {
            DoublyLinkedListNode node = cache.get(key);
            removeNode(node);
            addToEnd(node);
            return "value of the key " + key + " is: " + node.value;
        }
    }

    void addToEnd(DoublyLinkedListNode node) {
        DoublyLinkedListNode nodeBeforeTail = tail.prev;

        nodeBeforeTail.next = node;
        node.prev = nodeBeforeTail;

        node.next = tail;
        tail.prev = node;
    }

    void removeNode(DoublyLinkedListNode node) {
        DoublyLinkedListNode prevNode = node.prev;
        DoublyLinkedListNode nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}
