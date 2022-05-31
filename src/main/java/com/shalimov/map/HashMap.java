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
        growCapacity();
        int bucketNumber = getIndexOfBucket(key);
        Entry<K, V> entry = buckets[bucketNumber];
        if (entry == null) {
            Entry<K, V> newEntry = new Entry<>(key, value);
            buckets[bucketNumber] = newEntry;
            size++;
            return value;
        }
        if (entry.key.equals(key)) {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        } else {
            if (entry.next != null) {
                Entry<K, V> tempEntry = entry.next;
                if (tempEntry.key.equals(key)) {
                    V oldValue = tempEntry.value;
                    tempEntry.value = value;
                    return oldValue;
                }
                while (tempEntry.next != null) {

                    if (tempEntry.key.equals(key)) {
                        V oldValue = tempEntry.value;
                        tempEntry.value = value;
                        return oldValue;
                    }
                    tempEntry = tempEntry.next;
                }
                if (tempEntry.key.equals(key)) {
                    V oldValue = tempEntry.value;
                    tempEntry.value = value;
                    return oldValue;
                }
                tempEntry.next = new Entry<>(key, value);
            } else {
                entry.next = new Entry<>(key, value);
            }
            size++;
        }
        return value;
    }

    @Override
    public V remove(K key) {
        int bucketNumber = getIndexOfBucket(key);
        Entry<K, V> entry = buckets[bucketNumber];
        if (entry != null) {
            do {
                if (entry.getKey().equals(key)) {
                    V tmpValue = entry.value;
                    if (entry.next == null) {

                        buckets[bucketNumber] = null;
                    } else {
                        buckets[bucketNumber] = entry.next;
                    }
                    size--;
                    return tmpValue;
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
        int bucketNumber = getIndexOfBucket(key);
        Entry<K, V> entry = buckets[bucketNumber];
        if (isEmpty()) {
            return null;
        }
        if (entry.getKey() != null) {
            if (entry.getKey().equals(key)) {
                return entry.value;
            }
        } else {
            return entry.value;
        }

        while (entry.next != null) {
            entry = entry.next;
            return entry.value;
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
            ArrayList<Map.Entry<K, V>> entries = new ArrayList<>();
            Iterator<Map.Entry<K, V>> iterator = this.iterator();
            while (iterator.hasNext()) {
                Map.Entry<K, V> rr = iterator.next();
                entries.add(rr);
            }
            size = 0;
            buckets = newBuckets;
            for (Map.Entry<K, V> entry : entries) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private Entry currentEntry;
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
                    currentEntry = buckets[i];
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
                    }
                    if (currentEntry.next != null) {
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