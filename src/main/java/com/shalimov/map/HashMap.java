package com.shalimov.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {


    private final static int DEFAULT_INITIAL_CAPACITY = 5;
    private static final double GROW_FACTOR = 2.5;
    private static final double LOAD_FACTOR = 0.75;
    private Entry<K, V>[] buckets;
    private int size;

    private final double growFactor;
    private final double loadFactor;

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, LOAD_FACTOR, GROW_FACTOR);
    }

    public HashMap(int capacity, double loadFactor, double growFactor) {
        this.growFactor = growFactor;
        this.loadFactor = loadFactor;
        this.buckets = new Entry[capacity];
    }

    @Override
    public V put(K key, V value) {
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
                Entry<K, V> tempEntry;
                tempEntry = entry.next;
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

//        tempEntry = entry.next;
//        if (tempEntry != null) {
//            while (tempEntry.next != null) {
//                tempEntry = tempEntry.next;
//            }
//        }
//        Entry<K, V> newEntry = new Entry<>(key, value);
//        tempEntry.next = newEntry;
//        if (size > buckets.length * loadFactor) {
//            growCapacity();
//        }

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
                        entry = null;
                    } else {
                        entry = entry.next;
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
        if (entry.getKey().equals(key)) {
            return entry.value;
        }
        while (entry.next != null) {
            entry = entry.next;
            if (Objects.equals(entry.getKey(), key)) ;
            return entry.value;
        }
        return null;
    }


    @Override
    public boolean containsKey(K key) {
        if (isEmpty()) {
            return false;
        }
        Entry<K, V> entry = buckets[getIndexOfBucket(key)];
        if (entry == null) {
            return false;
        }
        while (entry != null) {
            if (Objects.equals(entry.getKey(), key)) ;
            return true;
        }
        entry = entry.next;

        return false;
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


//    private void growCapacity() {
//        ArrayList<Entry<K, V>> entries = new ArrayList<>();
//        for (int i = 0; i < buckets.length; i++) {
//            if (buckets[i] != null) {
//                ArrayList<Entry<K, V>> bucket = buckets[i];
//                entries.addAll(bucket);
//            }
//        }
//        ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[(int) (buckets.length * growFactor)];
//        buckets = newBuckets;
//        for (Entry<K, V> entry : entries) {
//            int bucketNumber = getIndexOfBucket(entry.key);
//            List<Entry<K, V>> bucket = buckets[bucketNumber];
//            if (bucket == null) {
//                bucket = new ArrayList<Entry<K, V>>();
//                buckets[bucketNumber] = (ArrayList<Entry<K, V>>) bucket;
//            }
//            for (Entry<K, V> oldEntry : bucket) {
//                if (entry.key.equals(oldEntry.key)) {
//                    oldEntry.value = entry.value;
//
//                }
//            }
//            if (size > buckets.length * loadFactor) {
//                growCapacity();
//            }
//            Entry<K, V> node = new Entry<>(entry.key, entry.value);
//            bucket.add(node);
//        }
//    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
//        private int bucketIndex;
//        private int bucketsIndex;
//        private boolean canRemove;
//        private final Iterator<Entry<K, V>> bucketIterator;


        private HashMapIterator() {
//            while (buckets[bucketIndex] == null) {
//                bucketIndex++;
//            }
//            bucketIterator = buckets[bucketIndex].iterator();
        }

        @Override
        public boolean hasNext() {
//            if (buckets[bucketIndex].size() > bucketsIndex) {
//                return true;
//            }
//            for (int i = bucketIndex; i < buckets.length; i++) {
//                if (buckets[i] != null) {
//                    if (!buckets[i].isEmpty()) {
//                        return true;
//                    }
//                }
//            }
            return false;
        }

        @Override
        public Map.Entry<K, V> next() {
//
//            if (!hasNext()) {
//                throw new NoSuchElementException("Next element is not exist");
//            }
//            ArrayList<Entry<K, V>> bucket = buckets[bucketIndex];
//            if (bucket.size() > bucketsIndex) {
//                canRemove = true;
//                return bucket.get(bucketsIndex++);
//
//            }
//            for (int i = bucketIndex + 1; i < buckets.length; i++) {
//                if (buckets[i] != null) {
//                    if (!buckets[i].isEmpty()) {
//                        bucketIndex = i;
//                        bucketsIndex = 0;
//                        canRemove = true;
//                        ArrayList<Entry<K, V>> entries = buckets[i];
//
//                        return entries.get(0);
//                    }
//                }
//            }
            return null;
        }


        @Override
        public void remove() {
//            if (!canRemove) {
//                throw new IllegalStateException("Method next() has not called before remove");
//            }
//            //bucketIndex was incremented for  method next(), so we must use bucketsIndex -1
//            Entry<K, V> entryToDeleted = buckets[bucketIndex].get(bucketsIndex - 1);
//            HashMap.this.remove(entryToDeleted.getKey());
//            canRemove = false;
        }


    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

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