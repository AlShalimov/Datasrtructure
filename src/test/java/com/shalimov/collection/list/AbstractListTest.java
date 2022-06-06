package com.shalimov.collection.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
    @Test
    public void testTestAddByFirstIndex() {
        list.add("6", 0);
        assertEquals("6", list.get(0));
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
    public void receiveIndexOfTheLastOccurrenceOfValue() {
        list.add("1");
        assertEquals(5, list.lastIndexOf("1"));
    }

    @Test
    public void testLastIndexOf() {
        list.add("1", 2);
        int actual = list.lastIndexOf("1");
        assertEquals(2, actual);
        assertEquals(-1, list.lastIndexOf("5"));
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
        assertEquals("[1,2,3,4,5]", emptyList.toString());
    }

    @Test
    public void testContains() {
        assertTrue(list.contains("4"));
        assertFalse(list.contains("8"));
    }

    @Test
    public void testContainsNull() {
        list.add(null);
        assertTrue(list.contains(null));
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
    public void testGetByIndexIfIndexBiggerSize() {
        emptyList.get(emptyList.size() + 1);
    }

    @Test
    public void testRemoveByIndexInTheMiddle() {
        Object removeValue = list.remove(3);
        assertEquals("3", removeValue);
        assertEquals("0", list.get(0));
        assertEquals("1", list.get(1));
        assertEquals("2", list.get(2));
        assertEquals("4", list.get(3));
        assertEquals(4, list.size());
    }
    @Test
    public void testRemoveByLastIndex() {
        list.remove(4);
        assertEquals(4, list.size());
        assertEquals("0", list.get(0));
        assertEquals("1", list.get(1));
        assertEquals("2", list.get(2));
        assertEquals("3", list.get(3));
        assertFalse(list.contains("4"));
    }
    @Test
    public void testRemoveByFirstIndex() {
        list.remove(0);
        assertEquals(4, list.size());
        assertEquals("1", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("3", list.get(2));
        assertEquals("4", list.get(3));
        assertFalse(list.contains("0"));
    }
    @Test
    public void testRemoveIfSizeIsOne() {
        emptyList.add("1");
        assertEquals(1,emptyList.size());
        emptyList.remove(0);
        assertTrue(emptyList.isEmpty());
    }
    @Test
    public void whenRemoveByIndexThenRemovedValueReturned() {
        Object removedValue = list.remove(0);
        assertEquals("0", removedValue);
    }
    @Test
    public void testIteratorHasNextReturnTrueWhenListNotEmpty() {
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextReturnFalseWhenEmptyList() {
        Iterator<String> iterator = emptyList.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNextIterator() {
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        String firstValue = iterator.next();
        assertEquals(list.get(0), firstValue);
        String secondValue = iterator.next();
        assertEquals(list.get(1), secondValue);
    }

    @Test
    public void testIteratorRemove() {
        Iterator<String> iterator = list.iterator();
        assertEquals(5, list.size());
        assertTrue(iterator.hasNext());
        iterator.remove();
        assertEquals("1", list.get(0));
        assertEquals(4, list.size());
    }
    @Test
    public void testThuIteratorRemove() {
        assertEquals(5, list.size());
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        assertEquals(3, list.size());
    }
    @Test
    public void testIteratorRemoveLastElement() {
        assertEquals(5, list.size());
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals(4, list.size());
    }

    @Test
    public void testIteratorRemoveWhenSizeIsOne() {
        assertEquals(0, emptyList.size());
        emptyList.add("1");
        Iterator<String> iterator = emptyList.iterator();
        iterator.remove();
        assertTrue(emptyList.isEmpty());
    }
    @Test(expected = NoSuchElementException.class)
    public void testIteratorNexOnEmptyList() {
        Iterator<String> iterator = emptyList.iterator();
        iterator.next();
    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveWhenEmptyList() {
        Iterator<String> iterator = emptyList.iterator();
        iterator.remove();
    }
}


