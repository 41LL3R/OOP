package ru.nsu.etronin.task131;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для поиска строкового паттерна в массиве символов.
 */

public class CharPatternMatcher {

    private final char[] pattern;

    public CharPatternMatcher(String pattern) {
        this.pattern = pattern.toCharArray();
    }

    public int length() {
        return pattern.length;
    }

    public boolean isEmpty() {
        return pattern.length == 0;
    }

    /**
     * Ищет все вхождения паттерна в заданном буфере.
     *
     * @param buffer массив символов для поиска
     * @param limit  количество валидных символов в буфере
     * @return список локальных индексов вхождений
     */

    public List<Integer> findAll(char[] buffer, int limit) {
        List<Integer> hits = new ArrayList<>();
        int m = pattern.length;
        int searchLimit = limit - m;

        for (int i = 0; i <= searchLimit; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (buffer[i + j] != pattern[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                hits.add(i);
            }
        }
        return hits;
    }
}