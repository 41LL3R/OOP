package ru.nsu.etronin.task122;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {

    private HashTable<String, Number> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
    }


    @Test
    void newTable_isEmpty() {
        assertEquals(0, table.size());
        assertFalse(table.iterator().hasNext());
    }


    @Test
    void putAndGet() {
        table.put("one", 1);
        assertEquals(1, table.get("one"));
        assertNull(table.get("missing"));
    }

    @Test
    void put_overwritesExisting() {
        table.put("one", 1);
        table.put("one", 100);
        assertEquals(1, table.size());
        assertEquals(100, table.get("one"));
    }

    @Test
    void update_works() {
        table.put("one", 1);
        table.update("one", 1.0);
        assertEquals(1.0, table.get("one"));

        assertThrows(NoSuchElementException.class, () -> table.update("missing", 1));
    }


    @Test
    void containsKey_works() {
        table.put("one", 1);
        assertTrue(table.containsKey("one"));
        assertFalse(table.containsKey("two"));
    }


    @Test
    void equalsAndHashCode() {
        HashTable<String, Number> t2 = new HashTable<>();
        table.put("a", 1);
        table.put("b", 2);
        t2.put("a", 1);
        t2.put("b", 2);

        assertEquals(table, t2);
        assertEquals(table.hashCode(), t2.hashCode());
        assertNotEquals(new HashTable<>(), table);
    }


    @Test
    void toString_works() {
        assertEquals("{}", table.toString());
        table.put("a", 1);
        assertTrue(table.toString().matches("\\{a=1}"));
    }


    @Test
    void collisions_handled() {
        table.put("Aa", 1);
        table.put("BB", 2);
        assertEquals(1, table.get("Aa"));
        assertEquals(2, table.get("BB"));
        assertTrue(table.remove("Aa"));
        assertEquals(2, table.get("BB"));
    }


    @Test
    void resize_keepsAllElements() {
        for (int i = 0; i < 100; i++) {
            table.put("key" + i, i);
        }
        assertEquals(100, table.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(i, table.get("key" + i));
        }
    }


    @Test
    void nullKey_supported() {
        table.put(null, 42);
        assertEquals(42, table.get(null));
        assertTrue(table.containsKey(null));
        assertTrue(table.remove(null));
    }
}