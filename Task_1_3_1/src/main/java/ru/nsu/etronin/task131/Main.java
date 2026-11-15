package ru.nsu.etronin.task131;

public class Main {
    public static void main(String[] args) throws Exception {
        try (java.io.FileWriter fw = new java.io.FileWriter("input.txt",
                java.nio.charset.StandardCharsets.UTF_8)) {
            fw.write("абракадабра");
        }

        SubstringFinder finder = new SubstringFinder();
        System.out.println(finder.find("input.txt", "бра"));
    }
}