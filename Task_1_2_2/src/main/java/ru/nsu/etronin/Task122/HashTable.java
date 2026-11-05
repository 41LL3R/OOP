package ru.nsu.etronin.Task122;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация хеш-таблицы.
 *
 * @param <K> тип ключа
 *
 * @param <V> тип значения
 */

public class HashTable<K, V> implements Iterable<Entry<K, V>> {

    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    Node<K, V>[] table;
    int size;
    int threshold;
    int modCount;

    public HashTable() {
        this.table = (Node<K, V>[]) new Node[INITIAL_CAPACITY];
        this.threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
    }

    int getIndex(Object key, int capacity) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        h ^= (h >>> 16);
        return h & (capacity - 1);
    }

    Node<K, V> findNode(Object key) {
        int index = getIndex(key, table.length);
        Node<K, V> current = table[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Кладет значение в таблицу по ключу.
     *
     * @param key ключ.
     *
     * @param value значение.
     */

    public void put(K key, V value) {
        Node<K, V> existing = findNode(key);
        if (existing != null) {
            existing.value = value;
            return;
        }

        if (size >= threshold) {
            resize();
        }

        int index = getIndex(key, table.length);
        table[index] = new Node<>(key, value, table[index]);
        size++;
        modCount++;
    }

    public V get(K key) {
        Node<K, V> node = findNode(key);
        return node == null ? null : node.value;
    }

    /**
     * Заменяет значение по ключу.
     *
     * @param key ключ.
     *
     * @param value новое значение.
     */

    public void update(K key, V value) {
        Node<K, V> node = findNode(key);
        if (node == null) {
            throw new NoSuchElementException("Key not found: " + key);
        }
        node.value = value;
    }

    public boolean containsKey(K key) {
        return findNode(key) != null;
    }

    /**
     * Удаляет значение для данного ключа.
     *
     * @param key ключ.
     *
     * @return true если удалено, иначе false.
     */

    public boolean remove(K key) {
        int index = getIndex(key, table.length);
        Node<K, V> prev = null;
        Node<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                modCount++;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    private void resize() {
        int newCapacity = table.length * 2;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];

        for (Node<K, V> head : table) {
            Node<K, V> current = head;
            while (current != null) {
                Node<K, V> next = current.next;
                int newIndex = getIndex(current.key, newCapacity);
                current.next = newTable[newIndex];
                newTable[newIndex] = current;
                current = next;
            }
        }
        table = newTable;
        threshold = (int) (newCapacity * LOAD_FACTOR);
    }

    void decrementSize() {
        size--;
    }

    void incrementModCount() {
        modCount++;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableIterator<>(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HashTable<?, ?> other = (HashTable<?, ?>) o;
        if (size != other.size) {
            return false;
        }

        for (Entry<K, V> entry : this) {
            Node<?, ?> otherNode = other.findNode(entry.getKey());
            if (otherNode == null || !Objects.equals(entry.getValue(), otherNode.value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int h = 0;
        for (Entry<K, V> entry : this) {
            h += Objects.hashCode(entry.getKey()) ^ Objects.hashCode(entry.getValue());
        }
        return h;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        Iterator<Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.append("}").toString();
    }

    public int size() {
        return size;
    }
}