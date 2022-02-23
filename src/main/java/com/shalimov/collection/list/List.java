package com.shalimov.collection.list;

public interface List {
   void  add(Object value);
   void add(Object value, int index);
   int size();
   void clear();
   int indexOf(Object value);
   int lastIndexOf(Object value);
   Object set(Object value, int index);
   boolean isEmpty();
   String toString();
   boolean contains(Object value);
   Object get(int index);
   Object remove(int index);
}
