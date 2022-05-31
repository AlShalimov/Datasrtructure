package com.shalimov.map;

import org.junit.Test;

import java.util.Iterator;

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
    public void NotNullKeyWhenPut_ThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        map.put("key", "value");
        assertEquals(1, map.size());
        assertEquals("value", map.get("key"));
    }

    @Test
    public void WhenPutSizeShouldBeEqualToSizeOfKeysAndGetByKeyReturnCorrectValue() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals(2, map.size());
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
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
        String key = "key";
        map.put(key, "value");
        assertEquals(1, map.size());

        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        iterator.next();
        iterator.remove();

        assertEquals(0, map.size());
    }

}


