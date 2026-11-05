package ru.nsu.etronin.task122;

/**
 * Неизменяемая пара ключ — значение.
 *
 * @param <K> тип ключа
 *
 * @param <V> тип значения
 */

public class Entry<K, V> {
    private final K key;
    private final V value;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}