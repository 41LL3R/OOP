package ru.nsu.etronin.task121;

import java.util.*;

public class AdjacencyMatrix<T> implements GraphStorage<T> {
    private final Map<T, Integer> vertexToIndex = new HashMap<>();
    private final Map<Integer, T> indexToVertex = new HashMap<>();

    private int[][] matrix = new int[10][10];
    private int vertexCount = 0;

    @Override
    public void addVertex(T vertex) {
        if (vertexToIndex.containsKey(vertex)) return;

        if (vertexCount >= matrix.length) {
            int newSize = matrix.length * 2;
            int[][] newMatrix = new int[newSize][newSize];
            for (int i = 0; i < matrix.length; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix.length);
            }
            matrix = newMatrix;
        }

        vertexToIndex.put(vertex, vertexCount);
        indexToVertex.put(vertexCount, vertex);
        vertexCount++;
    }

    @Override
    public void removeVertex(T vertex) {
        if (!vertexToIndex.containsKey(vertex)) return;

        int idxToRemove = vertexToIndex.get(vertex);

        for (int i = idxToRemove + 1; i < vertexCount; i++) {
            T v = indexToVertex.get(i);
            vertexToIndex.put(v, i - 1);
            indexToVertex.put(i - 1, v);
        }

        indexToVertex.remove(vertexCount - 1);
        vertexToIndex.remove(vertex);
        vertexCount--;

        int[][] newMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                int oldI = i >= idxToRemove ? i + 1 : i;
                int oldJ = j >= idxToRemove ? j + 1 : j;
                newMatrix[i][j] = matrix[oldI][oldJ];
            }
        }
        matrix = newMatrix;
    }

    @Override
    public void addEdge(T source, T target) {
        if (containsVertex(source) && containsVertex(target)) {
            matrix[vertexToIndex.get(source)][vertexToIndex.get(target)] = 1;
        }
    }

    @Override
    public void removeEdge(T source, T target) {
        if (containsEdge(source, target)) {
            matrix[vertexToIndex.get(source)][vertexToIndex.get(target)] = 0;
        }
    }

    @Override
    public List<T> getNeighbors(T vertex) {
        List<T> neighbors = new ArrayList<>();
        if (!containsVertex(vertex)) return neighbors;

        int row = vertexToIndex.get(vertex);
        for (int j = 0; j < vertexCount; j++) {
            if (matrix[row][j] == 1) {
                neighbors.add(indexToVertex.get(j));
            }
        }
        return neighbors;
    }

    @Override
    public boolean containsVertex(T vertex) {
        return vertexToIndex.containsKey(vertex);
    }

    @Override
    public boolean containsEdge(T source, T target) {
        return containsVertex(source) && containsVertex(target)
                && matrix[vertexToIndex.get(source)][vertexToIndex.get(target)] == 1;
    }

    @Override
    public List<T> getAllVertices() {
        return new ArrayList<>(vertexToIndex.keySet());
    }

    @Override
    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (matrix[i][j] == 1) count++;
            }
        }
        return count;
    }
}
