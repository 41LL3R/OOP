package ru.nsu.etronin.task121;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListTest {

    @Test
    void testGraphOperations() {
        AdjacencyList<String> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        assertTrue(graph.containsVertex("A"));
        assertEquals(3, graph.getAllVertices().size());

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        assertTrue(graph.containsEdge("A", "B"));
        assertEquals(2, graph.getNeighbors("A").size());
        assertEquals(2, graph.getEdgeCount());

        graph.removeEdge("A", "B");
        assertFalse(graph.containsEdge("A", "B"));
        assertEquals(1, graph.getEdgeCount());

        graph.removeVertex("A");
        assertFalse(graph.containsVertex("A"));
        assertFalse(graph.containsEdge("A", "C"));
        assertEquals(0, graph.getEdgeCount());
        
        assertTrue(graph.getNeighbors("Z").isEmpty());
        assertFalse(graph.containsEdge("X", "Y"));
    }
}