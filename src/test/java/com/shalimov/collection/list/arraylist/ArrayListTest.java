package com.shalimov.collection.list.arraylist;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest {

    ArrayList listWithZeroElement;
    ArrayList listWithFiveElement;
    ArrayList listWithTenElement;

    @Before
    public void before() {
        listWithZeroElement = new ArrayList();
        listWithFiveElement = new ArrayList();
        for (int i = 0; i < 5; i++) {
            listWithFiveElement.add(i);
        }
        listWithTenElement = new ArrayList();
        for (int i = 0; i < 10; i++) {
            listWithTenElement.add(i);
        }
    }

    @Test
    public void testSizeOnEmptyList() {
        assertEquals(0, listWithZeroElement.size());
    }

    @Test
    public void testSizeOnNonEmptyList() {
        assertEquals(5, listWithFiveElement.size());
    }

    @Test
    public void testTestAddByIndex() {
        listWithFiveElement.add(6, 1);
        assertEquals(6, listWithFiveElement.get(1));
        assertEquals(6, listWithFiveElement.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddByInvalidIndex() {
        listWithFiveElement.add(10, 10);
    }

    @Test
    public void testSize() {
        assertEquals(5, listWithFiveElement.size());
    }

    @Test
    public void testClear() {
        listWithFiveElement.clear();
        assertTrue(listWithFiveElement.isEmpty());
    }

    @Test
    public void testIndexOf() {
        listWithFiveElement.add(6, 1);
        assertEquals(6, listWithFiveElement.get(1));
        assertEquals(6, listWithFiveElement.size());
    }

    @Test
    public void testLastIndexOf() {
        listWithFiveElement.add(1, 2);
        int actual = listWithFiveElement.indexOf(1);
        assertEquals(1, actual);
    }

    @Test
    public void testSet() {
        Object changedValue = listWithFiveElement.set(7, 1);
        assertEquals(1, changedValue);
        assertEquals(7, listWithFiveElement.get(1));
    }

    @Test
    public void testTestToString() {
        listWithZeroElement.add('h');
        listWithZeroElement.add('e');
        listWithZeroElement.add('l');
        listWithZeroElement.add('l');
        listWithZeroElement.add('o');
        assertEquals("hello size=5", listWithZeroElement.toString());
    }

    @Test
    public void testContains() {
        boolean actual = listWithTenElement.contains(5);
        assertTrue(actual);
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, listWithTenElement.get(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIndexLessThenZero() {
        listWithZeroElement.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIndexAfterSize() {
        listWithZeroElement.get(6);
    }

    @Test
    public void testRemove() {
        Object removeValue = listWithFiveElement.remove(3);
        assertEquals(3, removeValue);
        assertEquals(4, listWithFiveElement.get(3));
        assertEquals(4, listWithFiveElement.size());
    }
}

