package ru.nsu.etronin.task121;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph<T> {
    private GraphStorage<T> storage;

    public Graph(GraphStorage<T> storage) {
        this.storage = storage;
    }

    public void addVertex(T vertex) { storage.addVertex(vertex); }
    public void removeVertex(T vertex) { storage.removeVertex(vertex); }
    public void addEdge(T source, T target) { storage.addEdge(source, target); }
    public void removeEdge(T source, T target) { storage.removeEdge(source, target); }
    public List<T> getNeighbors(T vertex) { return storage.getNeighbors(vertex); }
    public List<T> getAllVertices() { return storage.getAllVertices(); }

    public void readFromFile(String filename, Class<T> type) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 2) {
                    T source = parseVertex(parts[0], type);
                    T target = parseVertex(parts[1], type);

                    addVertex(source);
                    addVertex(target);
                    addEdge(source, target);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private T parseVertex(String val, Class<T> type) {
        if (type == Integer.class) {
            return (T) Integer.valueOf(val);
        }
        return (T) val;
    }

    public List<T> topologicalSort() {
        Map<T, Integer> inDegree = new HashMap<>();
        List<T> allVertices = getAllVertices();

        for (T v : allVertices) inDegree.put(v, 0);

        for (T v : allVertices) {
            for (T neighbor : getNeighbors(v)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        Queue<T> queue = new LinkedList<>();
        for (Map.Entry<T, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) queue.add(entry.getKey());
        }

        List<T> result = new ArrayList<>();
        int visitedCount = 0;

        while (!queue.isEmpty()) {
            T current = queue.poll();
            result.add(current);
            visitedCount++;

            for (T neighbor : getNeighbors(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (visitedCount != allVertices.size()) {
            throw new IllegalStateException("Граф содержит циклы. Топологическая сортировка невозможна.");
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph<?> graph = (Graph<?>) o;
        return getAllVertices().size() == graph.getAllVertices().size() &&
                storage.getEdgeCount() == graph.storage.getEdgeCount();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph:\n");
        for (T v : getAllVertices()) {
            sb.append(v).append(" -> ").append(getNeighbors(v)).append("\n");
        }
        return sb.toString();
    }
}
