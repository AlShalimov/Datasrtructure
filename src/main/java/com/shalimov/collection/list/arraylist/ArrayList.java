package com.shalimov.collection.list.arraylist;

import com.shalimov.collection.list.List;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 5;
    private int size;
    private T[] array;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int INITIAL_CAPACITY) {

        Object array = new Object[INITIAL_CAPACITY];
        this.array = (T[]) array;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
        validateIndexForAdd(index);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i <= size - 1; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        T tmp = array[index];
        array[index] = value;
        return tmp;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= size - 1; i++) {
            result.append(array[i]);
        }
        return result + " size=" + size;
    }


    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must be between 0 and " + (size - 1));
        }
        return array[index];
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T result = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return result;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == array.length) {
            Object[] tmpArray = new Object[(int) ((array.length * 1.5) + 1)];
            System.arraycopy(array, 0, tmpArray, 0, size);
            array = (T[]) tmpArray;
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must be between 0 and " + size);
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must be between 0 and " + size + 1);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next element is not exist");
            }
            return array[index++];
        }

        @Override
        public void remove() {
            if (array[index] == null) {
                throw new IllegalArgumentException("Element is not exist");
            } else {
                array[index] = null;
                size--;
            }
        }
    }
}



