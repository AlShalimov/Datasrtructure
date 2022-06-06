package com.shalimov.collection.list;

import java.util.Iterator;

public interface List<T> extends Iterable<T>{
    void add(T value);

    void add(T value, int index);

    int size();

    void clear();

    int indexOf(T value);

    int lastIndexOf(T value);

    T set(T value, int index);

    boolean isEmpty();

    String toString();

    boolean contains(T value);

    T get(int index);

    T remove(int index);


}
