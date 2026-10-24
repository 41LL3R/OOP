package ru.nsu.etronin.task121;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdjacencyMatrixTest {
    private AdjacencyMatrix<String> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyMatrix<>();
    }

    @Test
    void testAddAndRemoveVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertTrue(graph.containsVertex("A"));

        graph.removeVertex("A");
        assertFalse(graph.containsVertex("A"));
        assertTrue(graph.containsVertex("B"));
    }

    @Test
    void testAddAndRemoveEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        assertTrue(graph.containsEdge("A", "B"));
        assertFalse(graph.containsEdge("B", "A"));

        graph.removeEdge("A", "B");
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    void testGetNeighborsAndCount() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        assertEquals(2, graph.getNeighbors("A").size());
        assertEquals(2, graph.getEdgeCount());
        assertTrue(graph.getNeighbors("B").isEmpty());
    }

    @Test
    void testResizeMatrix() {
        for (int i = 0; i < 15; i++) {
            graph.addVertex(String.valueOf(i));
        }
        assertEquals(15, graph.getAllVertices().size());
        assertTrue(graph.containsVertex("14"));
    }
}