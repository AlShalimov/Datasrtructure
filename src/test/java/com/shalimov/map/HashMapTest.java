package com.shalimov.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class HashMapTest {

    Map<String, String> map = new HashMap<>();

    @Test
    public void whenMapIsEmptySizeEqualZero() {
        assertEquals(0, map.size());
    }

    @Test
    public void NotEmptyMapWhenRemoveSizeShouldBeEqualToZero() {
        map.put("key", "value");
        assertEquals(1, map.size());
        map.remove("key");
        assertEquals(0, map.size());
    }

    @Test
    public void NotNullKeyWhenPutThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        map.put("key", "value");
        assertEquals(1, map.size());
        assertEquals("value", map.get("key"));
    }

    @Test
    public void WhenPutSizeShouldBeEqualToSizeOfKeysAndGetByKeyReturnCorrectValue() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        assertEquals(3, map.size());
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
        assertEquals("value3", map.get("key3"));
    }

    @Test
    public void OverwriteValueWhenEnteringTheSameKey() {
        map.put("key", "value1");
        map.put("key", "value2");
        assertEquals(1, map.size());
        assertEquals("value2", map.get("key"));
    }

    @Test
    public void WhenPutValuesByDifferentKeysGetReturnsCorrectValue() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals(2, map.size());
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
    }

    @Test
    public void ReturnsValueFalseWithNullKeyOnEmptyMap() {
        assertFalse(map.containsKey(null));
    }

    @Test
    public void ReturnsTrueIfMapWithNullKey() {
        map.put(null, "value");
        assertTrue(map.containsKey(null));
    }

    @Test
    public void whenContainsNotExistingKeyThenReturnFalse() {
        map.put("key1", "value1");
        assertFalse(map.containsKey("key3"));
    }

    @Test
    public void givenExistingKeyWhenContainsKeyThenTrueShouldBeReturned() {
        map.put("key", "value");
        assertTrue(map.containsKey("key"));
    }

    @Test
    public void givenMapWithNotExistingNullKeyWhenContainsNullKeyThenFalseShouldBeReturned() {
        map.put("key", "value");
        assertFalse(map.containsKey(null));
    }

    @Test
    public void GetByKeyInEmptyMapReturnNull() {
        assertNull(map.get("key1"));
    }

    @Test
    public void whenMapIsEmptyThenReturnTrue() {
        assertTrue(map.isEmpty());
    }

    @Test
    public void whenMapIsNotEmptyThenReturnFalse() {
        map.put("key2", "value3");
        assertFalse(map.isEmpty());
    }

    @Test
    public void whenAddValueThenValueIsPresent() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void NotEmptyMapWhenIteratorHasNextThenReturnTrue() {
        map.put("key", "value");
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        map.remove("key");
        assertFalse(iterator.hasNext());
        map.put("key1", "value1");
        assertTrue(iterator.hasNext());
    }

    @Test
    public void WhenRemoveCalledAfterNextThenSizeShouldBeDecreasedByOneAndMapShouldNotContainKey() {
        map.put("key", "value");
        assertEquals(1, map.size());
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        iterator.next();
        iterator.remove();
        assertFalse(map.containsKey("key1"));
        assertEquals(0, map.size());
    }

    @Test
    public void givenNotEmptyMap_WhenIteratorHasNext_ThenShouldReturnTrue() {
        map.put("key", "value");
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        map.remove("key");
        assertFalse(iterator.hasNext());
        map.put("key", "value");
        assertTrue(iterator.hasNext());
    }

    @Test
    public void givenIteratorWhenRemoveCalledAfterNextThenSizeShouldBeDecreasedByOneAndMapShouldNotContainKey() {
        map.put("key", "value");
        assertEquals(1, map.size());
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(0, map.size());
        assertFalse(map.containsKey("key"));
    }

    @Test
    public void givenEmptyMapWhenRemoveThenSizeShouldBeEqualToZero() {
        map.remove("key");
        assertEquals(0, map.size());
    }

    @Test
    public void whenGetByExistingKeyGetByKeyReturnsCorrectValue() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
        assertEquals(2, map.size());
    }

    @Test
    public void TestRemoveWhenWhenOneBuckets() {
        map.put("key13", "value2");
        map.put("key24", "value3");
        map.remove("key13");
        map.remove("key24");
    }

    @Test(expected = IllegalStateException.class)
    public void whenRemoveWithOutNextArrayIllegalStateException() {
        map.put("key1", "value1");
        assertEquals(1, map.size());
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        iterator.remove();
    }

    @Test
    public void whenIteratorNextThenIteratorHasNextShouldReturnedTrue() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.hasNext();
        assertTrue(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCollIteratorNextAndIteratorHasNextIsFallsThenThenIsNoSuchElementException() {
        map.put("key1", "value1");
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.next();
    }
}



