package com.shalimov.map;

import org.junit.Test;

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
    public void GetByKeyInEmptyMapReturnNull(){
        assertNull(map.get("key1"));
    }
}


