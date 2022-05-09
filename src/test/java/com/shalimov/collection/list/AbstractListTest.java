package com.shalimov.collection.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;


public abstract class AbstractListTest {
    private List<String> list;
    private List<String> emptyList;

    @Before
    public void before() {
        emptyList = getListImpl();
        list = getListImpl();
        for (int i = 0; i < 5; i++) {
            list.add(String.valueOf(i));
        }
    }

    public abstract List<String> getListImpl();

    @Test
    public void testSizeOnEmptyList() {
        assertEquals(0, emptyList.size());
    }

    @Test
    public void testSizeOnNonEmptyList() {
        assertEquals(5, list.size());
    }

    @Test
    public void testTestAddByIndex() {
        list.add("6", 1);
        assertEquals("6", list.get(1));
        assertEquals(6, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddByInvalidIndex() {
        list.add("10", 10);
    }

    @Test
    public void testSize() {
        assertEquals(5, list.size());
    }

    @Test
    public void testClear() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIndexOf() {
        list.add("6", 1);
        assertEquals("6", list.get(1));
        assertEquals(6, list.size());
    }

    @Test
    public void testLastIndexOf() {
        list.add("1", 2);
        int actual = list.lastIndexOf("1");
        assertEquals(2, actual);
    }

    @Test
    public void testSet() {
        Object changedValue = list.set("7", 1);
        assertEquals("1", changedValue);
        assertEquals("7", list.get(1));
    }

    @Test
    public void testTestToString() {
        emptyList.add("1");
        emptyList.add("2");
        emptyList.add("3");
        emptyList.add("4");
        emptyList.add("5");
        assertEquals("12345 size=5", emptyList.toString());
    }

    @Test
    public void testContains() {

        assertTrue(list.contains("4"));
        assertFalse(list.contains("8"));
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 5; i++) {
            assertEquals(String.valueOf(i), list.get(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIndexLessThenZero() {
        emptyList.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIndexAfterSize() {
        emptyList.get(6);
    }

    @Test
    public void testRemove() {
        Object removeValue = list.remove(3);
        assertEquals("3", removeValue);
        assertEquals("4", list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    public void testIteratorHasNext() {
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testNextIterator() {
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
    }

    @Test
    public void testIteratorRemove() {
        Iterator<String> iterator = list.iterator();
        assertEquals(5, list.size());
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.remove();
        assertEquals(4, list.size());
    }
}


