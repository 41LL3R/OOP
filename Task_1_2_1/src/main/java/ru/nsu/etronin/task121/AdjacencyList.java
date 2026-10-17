package ru.nsu.etronin.task121;

import java.util.*;

public class AdjacencyList<T> implements GraphStorage<T> {
    private final Map<T, List<T>> adjacencyList = new HashMap<>();

    @Override
    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void removeVertex(T vertex) {
        adjacencyList.remove(vertex);
        for (List<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
    }

    @Override
    public void addEdge(T source, T target) {
        if (adjacencyList.containsKey(source) && adjacencyList.containsKey(target)) {
            adjacencyList.get(source).add(target);
        }
    }

    @Override
    public void removeEdge(T source, T target) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(target);
        }
    }

    @Override
    public List<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    @Override
    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    @Override
    public boolean containsEdge(T source, T target) {
        return adjacencyList.containsKey(source) && adjacencyList.get(source).contains(target);
    }

    @Override
    public List<T> getAllVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    @Override
    public int getEdgeCount() {
        int count = 0;
        for (List<T> list : adjacencyList.values()) count += list.size();
        return count;
    }
}