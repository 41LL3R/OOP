package ru.nsu.etronin.task121;

import java.util.List;

public interface GraphStorage<T> {
    void addVertex(T vertex);
    void removeVertex(T vertex);
    void addEdge(T source, T target);
    void removeEdge(T source, T target);
    List<T> getNeighbors(T vertex);
    boolean containsVertex(T vertex);
    boolean containsEdge(T source, T target);
    List<T> getAllVertices();
    int getEdgeCount();
}