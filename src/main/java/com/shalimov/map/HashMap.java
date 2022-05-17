package com.shalimov.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashMap<K, V> implements Map<K, V> {


    private final static int DEFAULT_INITIAL_CAPACITY = 5;
    private static final double GROW_FACTOR = 2.5;
    private static final double LOAD_FACTOR = 0.75;
    private ArrayList<Entry<K, V>>[] buckets;
    private int size;

    private double growFactor;
    private double loadFactor;

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, LOAD_FACTOR, GROW_FACTOR);
    }

    public HashMap(int capacity, double loadFactor, double growFactor) {
        this.growFactor = growFactor;
        this.loadFactor = loadFactor;
        this.buckets = new ArrayList[capacity];
    }

    @Override
    public V put(K key, V value) {
        int bucketNumber = getIndexOfBucket(key);
        List<Entry<K, V>> bucket = buckets[bucketNumber];
        if (bucket == null) {
            bucket = new ArrayList<Entry<K, V>>();
            buckets[bucketNumber] = (ArrayList<Entry<K, V>>) bucket;
        }
        for (Entry<K, V> entry : bucket) {
            if (key.equals(entry.key)) {
                V returnValue = entry.value;
                entry.value = value;
                return returnValue;
            }
        }
        Entry<K, V> node = new Entry<>(key, value);
        bucket.add(node);
        size++;
        return value;
    }

    @Override
    public V remove(K key) {
        int bucketNumber = getIndexOfBucket(key);
        List<Entry<K, V>> bucket = buckets[bucketNumber];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                V value = entry.value;
                entry = null;
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            List<Entry<K, V>> bucket = buckets[0];
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (entry.key == null) {
                        return entry.value;
                    }
                }
            }
        }
        int bucketNumber = getIndexOfBucket(key);
        List<Entry<K, V>> bucket = buckets[bucketNumber];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndexOfBucket(K key) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        if (hashCode == Integer.MIN_VALUE) {
            return 0;
        }
        return Math.abs(key.hashCode() % buckets.length);
    }

    private Entry<K, V> getEntry(K key) {

        return null;
    }


    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return null;
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }
    }
}

//    int bucketNumber= key.hashCode()%16;
//    List<Node<K,V>> bucket=tempName.get(bucketNumber);
//        for (Node<K,V> tempNode : bucket) {
//        if(tempNode.key.equals(key)){
//        V returnValue=  tempNode.value;
//        tempNode.value=value;
//        return returnValue;
//        }
//        }
//        Node<K,V>  node=new Node<>(key,value);
//        bucket.add(node);