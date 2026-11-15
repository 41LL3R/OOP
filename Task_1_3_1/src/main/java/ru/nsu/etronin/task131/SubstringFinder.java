package ru.nsu.etronin.task131;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SubstringFinder {

    private static final int DEFAULT_CHUNK_SIZE = 1024 * 1024;

    private final int chunkSize;

    public SubstringFinder() {
        this(DEFAULT_CHUNK_SIZE);
    }

    public SubstringFinder(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public List<Integer> find(String filePath, String target) throws IOException {
        CharPatternMatcher matcher = new CharPatternMatcher(target);
        if (matcher.isEmpty()) {
            return new ArrayList<>();
        }

        int overlap = matcher.length() - 1;
        Set<Integer> positions = new LinkedHashSet<>();

        try (FileChunkReader reader = new FileChunkReader(filePath, overlap, chunkSize)) {
            reader.forEachChunk((buffer, length, baseOffset) -> {
                for (int local : matcher.findAll(buffer, length)) {
                    positions.add(Math.toIntExact(baseOffset + local));
                }
            });
        }

        return new ArrayList<>(positions);
    }
}