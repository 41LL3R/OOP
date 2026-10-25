package ru.nsu.etronin.task121;

/**
 * Проверка работоспособности программы.
 */

public class Main {

    /**
     * Точка входа.
     *
     * @param args аргументы.
     */

    public static void main(String[] args) {
        Graph<String> stringGraph = new Graph<>(new AdjacencyList<>());

        stringGraph.addVertex("A");
        stringGraph.addVertex("B");
        stringGraph.addVertex("C");
        stringGraph.addEdge("A", "B");
        stringGraph.addEdge("A", "C");

        System.out.println("Строковый граф:");
        System.out.println(stringGraph);

        System.out.println("Топологическая сортировка: " + stringGraph.topologicalSort());
    }
}