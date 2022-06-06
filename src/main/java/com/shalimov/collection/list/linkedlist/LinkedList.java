package com.shalimov.collection.list.linkedlist;

import com.shalimov.collection.list.List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;


public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            head = tail = newNode;
        } else if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<T> temp = getNode(index);
            Node<T> current = temp.prev;
            current.next = newNode;
            newNode.prev = current;
            newNode.next = temp;
            temp.prev = newNode;
        }
        size++;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(",", "[", "]");
        for (T value : this) {
            result.add(String.valueOf(value));
        }
        return result.toString();
    }

    @Override
    public int indexOf(T value) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.value, value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(current.value, value)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        Node<T> current = getNode(index);
        T oldValue = current.value;
        current.value = value;
        return oldValue;
    }

    public T get(int index) {
        validateIndex(index);
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Node<T> current = getNode(index);
        if (size == 1) {
            head = tail = null;
        } else if (index == 0) {
            head.next.prev = null;
            head = head.next;
        } else if (index == size - 1) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
        return current.value;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;
        boolean canRemove;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next element is not exist");
            }
            T value = current.value;
            current = current.next;
            canRemove = true;
            return value;
        }

        @Override
        public void remove() {
            if (current == null) {
                throw new IllegalStateException("The method next() not used previously ");
            }
            if (size == 1) {
                head = tail = null;
            } else if (current == head) {
                tail.prev = null;
                head = current.next;
            } else if (current == tail) {
                tail = current.prev;
                tail.next = null;
            } else {
                Node<T> prevNode = current.prev;
                Node<T> nextNode = current.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }
            canRemove = false;
            size--;
        }
    }

    private Node<T> getNode(int index) {
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private static class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private T value;

        private Node(T value) {
            this.value = value;
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



