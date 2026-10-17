package ru.nsu.etronin.task121;

import java.util.*;

public class IncidenceMatrix<T> implements GraphStorage<T> {
    private final Map<T, Integer> vertexToIndex = new HashMap<>();
    private final Map<Integer, T> indexToVertex = new HashMap<>();

    private final List<int[]> edges = new ArrayList<>();

    private int[][] matrix = new int[10][10];
    private int vertexCount = 0;

    @Override
    public void addVertex(T vertex) {
        if (vertexToIndex.containsKey(vertex)) return;

        if (vertexCount >= matrix.length) {
            int[][] newMatrix = new int[matrix.length * 2][matrix.length * 2];
            for (int i = 0; i < matrix.length; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
            }
            matrix = newMatrix;
        }

        vertexToIndex.put(vertex, vertexCount);
        indexToVertex.put(vertexCount, vertex);
        vertexCount++;
    }

    @Override
    public void removeVertex(T vertex) {
        if (!containsVertex(vertex)) return;
        int idxToRemove = vertexToIndex.get(vertex);

        for (int i = edges.size() - 1; i >= 0; i--) {
            int[] edge = edges.get(i);
            if (edge[0] == idxToRemove || edge[1] == idxToRemove) {
                removeEdgeByColumnIndex(i);
            }
        }

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
            int oldI = i >= idxToRemove ? i + 1 : i;
            System.arraycopy(matrix[oldI], 0, newMatrix[i], 0, edges.size());
        }
        matrix = newMatrix;
    }

    @Override
    public void addEdge(T source, T target) {
        if (!containsVertex(source) || !containsVertex(target)) return;
        if (containsEdge(source, target)) return;

        int srcIdx = vertexToIndex.get(source);
        int tgtIdx = vertexToIndex.get(target);
        int edgeIdx = edges.size();

        if (edgeIdx >= matrix[0].length) {
            int newWidth = matrix[0].length * 2;
            int[][] newMatrix = new int[matrix.length][newWidth];
            for (int i = 0; i < matrix.length; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
            }
            matrix = newMatrix;
        }

        edges.add(new int[]{srcIdx, tgtIdx});

        matrix[srcIdx][edgeIdx] = 1;
        matrix[tgtIdx][edgeIdx] = -1;
    }

    @Override
    public void removeEdge(T source, T target) {
        if (!containsEdge(source, target)) return;
        int srcIdx = vertexToIndex.get(source);
        int tgtIdx = vertexToIndex.get(target);

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i)[0] == srcIdx && edges.get(i)[1] == tgtIdx) {
                removeEdgeByColumnIndex(i);
                break;
            }
        }
    }

    private void removeEdgeByColumnIndex(int colIdx) {
        edges.remove(colIdx);
        for (int i = 0; i < vertexCount; i++) {
            for (int j = colIdx; j < edges.size(); j++) {
                matrix[i][j] = matrix[i][j + 1];
            }
            matrix[i][edges.size()] = 0;
        }
    }

    @Override
    public List<T> getNeighbors(T vertex) {
        List<T> neighbors = new ArrayList<>();
        if (!containsVertex(vertex)) return neighbors;
        int row = vertexToIndex.get(vertex);

        for (int j = 0; j < edges.size(); j++) {
            if (matrix[row][j] == 1) {
                int targetIdx = edges.get(j)[1];
                neighbors.add(indexToVertex.get(targetIdx));
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
        if (!containsVertex(source) || !containsVertex(target)) return false;
        int srcIdx = vertexToIndex.get(source);
        int tgtIdx = vertexToIndex.get(target);

        for (int[] edge : edges) {
            if (edge[0] == srcIdx && edge[1] == tgtIdx) return true;
        }
        return false;
    }

    @Override
    public List<T> getAllVertices() {
        return new ArrayList<>(vertexToIndex.keySet());
    }

    @Override
    public int getEdgeCount() {
        return edges.size();
    }
}