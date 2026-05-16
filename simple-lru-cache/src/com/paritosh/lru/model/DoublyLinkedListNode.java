package com.paritosh.lru.model;

public class DoublyLinkedListNode {
    public String key;
    public String value;
    public DoublyLinkedListNode prev;
    public DoublyLinkedListNode next;

    public DoublyLinkedListNode(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
