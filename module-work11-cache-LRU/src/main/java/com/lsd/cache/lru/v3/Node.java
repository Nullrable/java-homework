package com.lsd.cache.lru.v3;

/**
 * @author nhsoft.lsd
 */
public class Node<K, V> {

    protected K key;
    protected V value;
    protected Node pre, next;

    public Node() {
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
