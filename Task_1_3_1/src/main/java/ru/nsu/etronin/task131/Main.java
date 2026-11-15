package ru.nsu.etronin.task131;

/**
 * Главный класс.
 */

public class Main {

    /**
     * Запуск примера.
     * @param args аргументы
     * @throws Exception при ошибках
     */

    public static void main(String[] args) throws Exception {
        try (java.io.FileWriter fw = new java.io.FileWriter("input.txt",
                java.nio.charset.StandardCharsets.UTF_8)) {
            fw.write("абракадабра");
        }

        SubstringFinder finder = new SubstringFinder();
        System.out.println(finder.find("input.txt", "бра"));
    }
}