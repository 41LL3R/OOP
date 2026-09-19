package ru.nsu.etronin.task111;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SortTest {

    @Test
    void reverseArray() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        Sort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testRandomArray() {
        int[] input = {12, 11, 13, 5, 6, 7};
        int[] expected = {5, 6, 7, 11, 12, 13};
        Sort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testAlreadySorted() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        Sort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testEmptyArray() {
        int[] input = {};
        int[] expected = {};
        Sort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSingleElement() {
        int[] input = {42};
        int[] expected = {42};
        Sort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testDuplicateElements() {
        int[] input = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9};
        Sort.sort(input);
        assertArrayEquals(expected, input);
    }
}