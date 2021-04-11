package com.lsd.cache.lru.v3;

/**
 * @author nhsoft.lsd
 */
public class LRUCache<K, V> {

    private BaseLRUCache<K, V>[] segments;

    public LRUCache(final int mapCapacity) {

        int cores = Runtime.getRuntime().availableProcessors(); //CPU核心数
        cores = cores < 2 ? 2 : cores;

        segments = new BaseLRUCache[cores];

        int segmentMapCapacity = mapCapacity / cores;

        int rest = mapCapacity % cores;

        for (int i = 0; i < cores; i++) {
            if (i < rest) {
                segments[i] = new BaseLRUCache(segmentMapCapacity++);
            }else {
                segments[i] = new BaseLRUCache(segmentMapCapacity);
            }
        }
    }

    public BaseLRUCache<K, V> cache(K k) {
        int hashCode = Math.abs(k.hashCode() * 31);
        int index = hashCode % segments.length;
        return segments[index];
    }

    public void put(K k, V v) {
        cache(k).put(k, v);
    }

    public V get(K k) {
        return cache(k).get(k);
    }
}
