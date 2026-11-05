package ru.nsu.etronin.Task122;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableIteratorTest {

    private HashTable<String, Number> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);
    }


    @Test
    void iterator_visitsAllElements() {
        int count = 0;
        for (Entry<String, Number> entry : table) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    void iterator_emptyTable() {
        HashTable<String, Number> empty = new HashTable<>();
        Iterator<Entry<String, Number>> it = empty.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }


    @Test
    void iterator_putDuringIteration_throws() {
        assertThrows(ConcurrentModificationException.class, () -> {
            for (Entry<String, Number> ignored : table) {
                table.put("four", 4);
            }
        });
    }

    @Test
    void iterator_removeDuringIteration_throws() {
        assertThrows(ConcurrentModificationException.class, () -> {
            for (Entry<String, Number> ignored : table) {
                table.remove("one");
            }
        });
    }


    @Test
    void iterator_removeWorks() {
        Iterator<Entry<String, Number>> it = table.iterator();
        while (it.hasNext()) {
            if ("two".equals(it.next().getKey())) {
                it.remove();
            }
        }
        assertEquals(2, table.size());
        assertFalse(table.containsKey("two"));
        assertTrue(table.containsKey("one"));
        assertTrue(table.containsKey("three"));
    }

    @Test
    void iterator_removeWithoutNext_throws() {
        Iterator<Entry<String, Number>> it = table.iterator();
        assertThrows(IllegalStateException.class, it::remove);

        it.next();
        it.remove();
        assertThrows(IllegalStateException.class, it::remove);
    }
}