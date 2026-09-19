package ru.nsu.etronin.task111;

/**
 * Класс реализует алгоритм пирамидальной сортировки.
 */

public class Sort {

    /**
     * Пирамидальная сортировка.
     *
     * @param arr Массив, который нужно отсортировать.
     */

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            sift(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            sift(arr, i, 0);
        }
    }

    /**
     * Просеивание поддерева с корнем в узле i (индекс в arr[]).
     * n - размер кучи.
     */

    private static void sift(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            sift(arr, n, largest);
        }
    }
}
