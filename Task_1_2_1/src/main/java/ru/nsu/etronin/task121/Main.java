package ru.nsu.etronin.task121;

public class Main {
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