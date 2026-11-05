package ru.nsu.etronin.task122;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class HashTableIterator<K, V> implements Iterator<Entry<K, V>> {
    private final HashTable<K, V> table;
    private int expectedModCount;
    private int index = 0;
    private Node<K, V> nextNode = null;
    private Node<K, V> lastReturned = null;

    HashTableIterator(HashTable<K, V> table) {
        this.table = table;
        this.expectedModCount = table.modCount;

        while (index < table.table.length && table.table[index] == null) {
            index++;
        }
        if (index < table.table.length) {
            nextNode = table.table[index];
        }
    }

    private void checkForComodification() {
        if (table.modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override
    public boolean hasNext() {
        checkForComodification();
        return nextNode != null;
    }

    @Override
    public Entry<K, V> next() {
        checkForComodification();
        if (nextNode == null) {
            throw new NoSuchElementException();
        }

        lastReturned = nextNode;
        nextNode = nextNode.next;

        if (nextNode == null) {
            index++;
            while (index < table.table.length && table.table[index] == null) {
                index++;
            }
            if (index < table.table.length) {
                nextNode = table.table[index];
            }
        }

        return new Entry<>(lastReturned.key, lastReturned.value);
    }

    @Override
    public void remove() {
        if (lastReturned == null) {
            throw new IllegalStateException();
        }
        checkForComodification();

        int i = table.getIndex(lastReturned.key, table.table.length);
        Node<K, V> prev = null;
        Node<K, V> current = table.table[i];

        while (current != null) {
            if (current == lastReturned) {
                if (prev == null) {
                    table.table[i] = current.next;
                } else {
                    prev.next = current.next;
                }
                table.decrementSize();
                table.incrementModCount();
                expectedModCount++;
                lastReturned = null;
                return;
            }
            prev = current;
            current = current.next;
        }
    }
}