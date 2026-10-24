package ru.nsu.etronin.task121;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {

    @Test
    void testTopologicalSortSuccess() {
        Graph<String> graph = new Graph<>(new AdjacencyList<>());
        graph.addVertex("A"); graph.addVertex("B"); graph.addVertex("C"); graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");

        List<String> sorted = graph.topologicalSort();
        assertEquals("A", sorted.get(0));
        assertEquals("D", sorted.get(sorted.size() - 1));
    }

    @Test
    void testTopologicalSortCycle() {
        Graph<String> graph = new Graph<>(new AdjacencyList<>());
        graph.addVertex("A"); graph.addVertex("B"); graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");

        assertThrows(IllegalStateException.class, graph::topologicalSort);
    }

    @Test
    void testReadFromFile(@TempDir Path tempDir) throws IOException {
        // Создаем временный файл
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "1 2\n2 3\n\n  \n3 4");

        Graph<Integer> graph = new Graph<>(new AdjacencyList<>());
        graph.readFromFile(file.toString(), Integer::valueOf);

        assertEquals(4, graph.getAllVertices().size());
        assertTrue(graph.containsEdge(1, 2));
        assertTrue(graph.containsEdge(3, 4));
    }

    @Test
    void testEqualsAndToString() {
        Graph<String> g1 = new Graph<>(new AdjacencyList<>());
        Graph<String> g2 = new Graph<>(new AdjacencyList<>());

        g1.addVertex("A"); g1.addVertex("B");
        g2.addVertex("A"); g2.addVertex("B");

        g1.addEdge("A", "B");
        g2.addEdge("A", "B");

        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
        assertTrue(g1.toString().contains("A -> [B]"));
    }
}