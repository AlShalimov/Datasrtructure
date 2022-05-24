package com.shalimov.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {


    private final static int DEFAULT_INITIAL_CAPACITY = 5;
    private static final double GROW_FACTOR = 2.5;
    private static final double LOAD_FACTOR = 0.75;
    private ArrayList<Entry<K, V>>[] buckets;
    private int size;

    private final double growFactor;
    private final double loadFactor;

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
        if (size > buckets.length * loadFactor) {
            growCapacity();
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
                bucket.remove(entry);
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

    private void growCapacity() {
        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                ArrayList<Entry<K, V>> bucket = buckets[i];
                entries.addAll(bucket);
            }
        }
        ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[(int) (buckets.length * growFactor)];
        buckets = newBuckets;
        for (Entry<K, V> entry : entries) {
            int bucketNumber = getIndexOfBucket(entry.key);
            List<Entry<K, V>> bucket = buckets[bucketNumber];
            if (bucket == null) {
                bucket = new ArrayList<Entry<K, V>>();
                buckets[bucketNumber] = (ArrayList<Entry<K, V>>) bucket;
            }
            for (Entry<K, V> oldEntry : bucket) {
                if (entry.key.equals(oldEntry.key)) {
                    oldEntry.value = entry.value;

                }
            }
            if (size > buckets.length * loadFactor) {
                growCapacity();
            }
            Entry<K, V> node = new Entry<>(entry.key, entry.value);
            bucket.add(node);
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private int bucketIndex;
        private int bucketsIndex ;
        private boolean canRemove;
        private final Iterator<Entry<K, V>> bucketIterator;


        private HashMapIterator() {
            while (buckets[bucketIndex] == null) {
                bucketIndex++;
            }
            bucketIterator = buckets[bucketIndex].iterator();
        }

        @Override
        public boolean hasNext() {
            if (buckets[bucketIndex].size() > bucketsIndex) {
                return true;
            }
            for (int i = bucketIndex; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    if (!buckets[i].isEmpty()) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public Map.Entry<K, V> next() {

            if (!hasNext()) {
                throw new NoSuchElementException("Next element is not exist");
            }
            ArrayList<Entry<K, V>> bucket = buckets[bucketIndex];
            if (bucket.size() > bucketsIndex) {
                canRemove = true;
                return bucket.get(bucketsIndex++);

            }
            for (int i = bucketIndex + 1; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    if (!buckets[i].isEmpty()) {
                        bucketIndex = i;
                        bucketsIndex = 0;
                        canRemove = true;
                        ArrayList<Entry<K, V>> entries = buckets[i];

                        return entries.get(0);
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
            //bucketIndex was incremented for  method next(), so we must use bucketsIndex -1
            Entry<K, V> entryToDeleted = buckets[bucketIndex].get(bucketsIndex -1);
            HashMap.this.remove(entryToDeleted.getKey());
            canRemove = false;
        }


    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
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