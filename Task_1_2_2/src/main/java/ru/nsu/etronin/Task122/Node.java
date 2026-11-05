package ru.nsu.etronin.Task122;

class Node<K, V> {
    final K key;
    V value;
    Node<K, V> next;

    Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}