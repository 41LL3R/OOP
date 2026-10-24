package ru.nsu.etronin.task121;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncidenceMatrixTest {
    private IncidenceMatrix<String> graph;

    @BeforeEach
    void setUp() {
        graph = new IncidenceMatrix<>();
    }

    @Test
    void testAddAndRemoveEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        assertTrue(graph.containsEdge("A", "B"));
        graph.removeEdge("A", "B");
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    void testRemoveVertexWithEdges() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        graph.removeVertex("A");
        assertFalse(graph.containsVertex("A"));
        assertEquals(0, graph.getEdgeCount());
    }

    @Test
    void testGetNeighbors() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        assertEquals(2, graph.getNeighbors("A").size());
    }
}