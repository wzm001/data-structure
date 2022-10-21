package com.wzm.ds.hash;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapsTest {

    @Test
    @DisplayName("散列冲突")
    void testHashConflict() {
        _HashTable<Key, String> hashTable = Maps.newHashTable();
        hashTable.put(Key.of(1), "a");
        hashTable.put(Key.of(2), "b");
        hashTable.put(Key.of(3), "c");
        hashTable.put(Key.of(4), "d");
        System.out.println(hashTable);
        assertEquals(4, hashTable.size());

        assertTrue(hashTable.remove(Key.of(3)));
        assertFalse(hashTable.remove(Key.of(3)));
        System.out.println(hashTable);
    }

    private static final class Key {
        private final int key;

        private Key(int key) {
            this.key = key;
        }

        static Key of(int i) {
            return new Key(i);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key1 = (Key) o;
            return key == key1.key;
        }

        @Override
        public int hashCode() {
            return 100;
        }

        @Override
        public String toString() {
            return "" + key;
        }
    }

    @Test
    @DisplayName("散列表")
    void newHashTable() {
        _HashTable<String, Integer> hashTable = Maps.newHashTable();
        assertTrue(hashTable.isEmpty());

        hashTable.put("a", 1);
        hashTable.put("b", 2);
        hashTable.put("c", 3);
        hashTable.put("d", 4);
        hashTable.put("d", 5);
        System.out.println(hashTable);

        assertFalse(hashTable.isEmpty());
        assertEquals(4, hashTable.size());
        assertEquals(hashTable.get("a"), 1);
        assertEquals(hashTable.get("b"), 2);
        assertEquals(hashTable.get("c"), 3);
        assertEquals(hashTable.get("d"), 5);

        hashTable.put(null, null);
        assertNull(hashTable.get(null));
        hashTable.put(null, 2);
        assertEquals(hashTable.get(null), 2);
        System.out.println(hashTable);

        assertTrue(hashTable.remove(null));
        assertFalse(hashTable.remove(null));
    }
}