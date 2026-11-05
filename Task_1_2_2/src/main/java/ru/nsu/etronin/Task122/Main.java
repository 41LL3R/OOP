package ru.nsu.etronin.Task122;

import java.util.ConcurrentModificationException;

/**
 * Тестовая программа.
 */

public class Main {

    /**
     * Точка входа тестовой программы.
     *
     * @param args аргументы
     */

    public static void main(String[] args) {
        HashTable<String, Number> hashTable = new HashTable<>();

        hashTable.put("one", 1);
        hashTable.update("one", 1.0);
        System.out.println(hashTable.get("one"));

        hashTable.put("two", 2);
        hashTable.put("three", 3);

        HashTable<String, Number> table2 = new HashTable<>();
        table2.put("one", 1.0);
        table2.put("two", 2);
        table2.put("three", 3);

        System.out.println("Равенство таблиц: " + hashTable.equals(table2));
        System.out.println("Содержимое таблицы: " + hashTable);

        System.out.println("\nТест итератора:");
        try {
            for (Entry<String, Number> entry : hashTable) {
                System.out.println("Элемент: " + entry);
                if (entry.getKey().equals("one")) {
                    hashTable.put("four", 4);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Поймано ConcurrentModificationException");
        }
    }
}