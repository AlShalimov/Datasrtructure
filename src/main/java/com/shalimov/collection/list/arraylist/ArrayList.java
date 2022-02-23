package com.shalimov.collection.list.arraylist;

import com.shalimov.collection.list.List;

public class ArrayList implements List {
    private static final int INITIAL_CAPACITY = 5;
    private int size;
    private Object[] array;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    public ArrayList(int INITIAL_CAPACITY) {
       array = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        ensureCapacity();
        validateIndexForAdd(index);
        System.arraycopy(array, 0, array, index, size - index);
        array[index] = value;
        size++;
    }

    public boolean isEmpty() {
        return size == 0 ;
    }


    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i <= size - 1; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object set(Object value, int index) {
        validateIndex(index);
        Object tmp = array[index];
        array[index] = value;
        return tmp;
    }


    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i <= size - 1; i++) {
            result = result + array[i];
        }
        return result + " size=" + size;
    }


    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must be between 0 and " + (size - 1));
        }
        return array[index];
    }

    @Override
    public Object remove(int index) {
        validateIndex(index);
        Object result = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return result;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            Object[] tmpArray = new Object[(int) ((array.length * 1.5) + 1)];
            System.arraycopy(array, 0, tmpArray, 0, size);
            array = tmpArray;
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
}


