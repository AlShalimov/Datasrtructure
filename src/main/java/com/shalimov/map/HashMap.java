package com.shalimov.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {

    private final static int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private static final double INCREASE_STEP = 2;
    private Entry<K, V>[] buckets;
    private int size;


    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    public HashMap(int capacity) {
        this.buckets = new Entry[capacity];
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> oldEntry = (Entry<K, V>) getEntry(key);
        if (oldEntry != null) {
            V oldValue = oldEntry.getValue();
            oldEntry.setValue(value);
            return oldValue;
        }else if (size>buckets.length*LOAD_FACTOR){
            growCapacity();
        }
        innerPut(new Entry<>(key,value));
        size++;
            return null;
    }

    @Override
    public V remove(K key) {
        int bucketNumber = getIndexOfBucket(key);
        Entry<K, V> entry = buckets[bucketNumber];
        if (entry != null) {
            do {
                if (entry.getKey().equals(key)) {
                    V tempValue = entry.value;
                    if (entry.next == null) {

                        buckets[bucketNumber] = null;
                    } else {
                        buckets[bucketNumber] = entry.next;
                    }
                    size--;
                    return tempValue;
                } else {
                    entry = entry.next;
                }
            }
            while (entry.next != null);
        }
        return null;
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = (Entry<K, V>) getEntry(key);
        if (entry!=null){
            return entry.value;
        }
            return null;
    }

    private Map.Entry<K,V> getEntry(K key) {
        int indexOfBucket = getIndexOfBucket(key);
        if (buckets[indexOfBucket] == null) {
            return null;
        }
        Iterator<Map.Entry<K, V>> iterator = iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> entry =  iterator.next();
            if (Objects.equals(entry.getKey(), key)) {
                return  entry;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        V value = get(key);
        return value != null;
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

    private void growCapacity() {
        if (buckets.length * LOAD_FACTOR < size) {
            int capacity = (int) (buckets.length * INCREASE_STEP);
            Entry<K, V>[] newBuckets = new Entry[capacity];
            for (Map.Entry<K, V> entry : this) {
                innerPut(newBuckets, entry);
            }
            buckets = newBuckets;
        }
    }
    private void innerPut(Entry<K, V> entry) {
        innerPut(buckets, entry);
    }

    private void innerPut(Entry<K, V>[] newBuckets, Map.Entry<K, V> entry) {
        K key = entry.getKey();
        int indexOfBucket = getIndexOfBucket(key);
        if (newBuckets[indexOfBucket] == null) {
            newBuckets[indexOfBucket] = new Entry<>(entry.getKey(), entry.getValue());
        }
        newBuckets[indexOfBucket].setValue(entry.getValue());
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private int bucketIndex;
        private int currentCountEntry;
        private int currentEntryAmountInBucket;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            return currentCountEntry < size;
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next element is not exist");
            }
            for (int i = bucketIndex; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    bucketIndex = i;
                    Entry<K, V> currentEntry = buckets[i];
                    Entry<K, V> value = currentEntry;
                    if (currentEntryAmountInBucket == 0) {
                        currentEntryAmountInBucket++;
                        currentCountEntry++;
                        canRemove = true;
                        return value;
                    }
                    for (int j = 0; j < currentEntryAmountInBucket - 1; j++) {
                        currentEntry = currentEntry.next;
                    }
                    if (null != currentEntry.next) {
                        currentEntry = currentEntry.next;
                        currentCountEntry++;
                        currentEntryAmountInBucket++;
                        canRemove = true;
                        return currentEntry;
                    } else {
                        bucketIndex++;
                        currentEntryAmountInBucket = 0;
                    }
                }
            }
            return null;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Method next() has not called before remove");
            }
            Entry<K, V> entryToBeDeleted = buckets[bucketIndex];
            if (currentEntryAmountInBucket > 1) {
                for (int i = 0; i < currentCountEntry; i++) {
                    entryToBeDeleted = entryToBeDeleted.next;
                }
            }
            HashMap.this.remove(entryToBeDeleted.key);
            canRemove = false;
            currentCountEntry--;
        }
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        private Entry(K key, V value) {
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