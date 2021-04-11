package com.lsd.cache.lru.v1;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU cache算法是，最近最少使用算法，将最近使用的对象放到队尾，如果超出maxCapacity，则从对首开始删除.
 * V1版本有个缺陷就是并不是线程安全的，需要对其优化
 * @author nhsoft.lsd
 */
public class LRUCache<K, V> {

    private Map<K, Node<K, V>> map; //引入map的目的是为了快速获取value
    private Node<K, V> header, tail; //哨兵

    private int maxCapacity; //cache的最大容量

    public LRUCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        map = new HashMap<>();
    }

    public void put(K k, V v) {
        if (map.containsKey(k)) {
            //如果放入 k,v，则判断当前散列表中是否包含值，如果包含，则更新value，并将节点放到队尾
            Node node = map.get(k);
            node.value = v;
            remove(node);
            add(node);
        } else {
            if (map.size() >= maxCapacity) {
                map.remove(header.key);
                remove(header);
            }
            Node node = new Node(k, v);
            map.remove(node.key);
            add(node);
        }
    }

    public V get(K k) {
       Node<K, V> node = map.get(k);
       if (node == null) {
           return null;
       }
       remove(node);
       add(node);
       return node.value;
    }

    public int size() {
        return map.size();
    }

    private void add(Node<K, V> node) {

        if (node == null) {
            return;
        }

        if (header == null) {
            header = node;
            tail = header;
        } else {
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;
        }
    }

    private void remove(Node<K, V> node) {

        if (node.pre == null) {
            header = node.next;
        } else {
            node.pre.next = node.next;
        }

        if (node.next == null) {
            tail = node.pre;
        } else {
            node.next.pre = node.pre;
        }
    }
}
