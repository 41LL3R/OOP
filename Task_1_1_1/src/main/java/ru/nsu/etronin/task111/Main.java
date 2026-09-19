package ru.nsu.etronin.task111;

import java.util.Arrays;

/**
 * Запуск теста из задания.
 */

public class Main {
    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1};
        System.out.println("До сортировки:    " + Arrays.toString(arr));
        Sort.sort(arr);
        System.out.println("После сортировки: " + Arrays.toString(arr));
    }
}
