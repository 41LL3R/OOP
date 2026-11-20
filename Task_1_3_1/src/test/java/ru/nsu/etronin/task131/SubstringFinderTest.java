package ru.nsu.etronin.task131;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SubstringFinderTest {

    @TempDir
    Path tmp;

    private Path write(String content) throws IOException {
        Path p = tmp.resolve("input.txt");
        Files.writeString(p, content, StandardCharsets.UTF_8);
        return p;
    }

    @Test
    void exampleFromTask() {
        try {
            Path p = write("абракадабра");
            assertEquals(List.of(1, 8),
                    new SubstringFinder().find(p.toString(), "бра"));
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void matchOnChunkBoundaryWithoutDuplicates() {
        try {
            Path p = write("abcdXYzw");
            SubstringFinder finder = new SubstringFinder(4);
            assertEquals(List.of(3), finder.find(p.toString(), "dXY"));
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void targetLongerThanChunk() {
        try {
            Path p = write("xxabcdefyy");
            SubstringFinder finder = new SubstringFinder(2);
            assertEquals(List.of(2), finder.find(p.toString(), "abcdef"));
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void emptyInputsReturnEmpty() {
        try {
            Path p = write("abc");
            assertTrue(new SubstringFinder().find(p.toString(), "").isEmpty());
            Path empty = write("");
            assertTrue(new SubstringFinder().find(empty.toString(), "a").isEmpty());
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void indicesAreCharIndicesNotBytes() {
        try {
            Path p = write("привет мир");
            assertEquals(List.of(7),
                    new SubstringFinder().find(p.toString(), "мир"));
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void findsMatchInLargeFile() throws IOException {
        Path file = tmp.resolve("large.txt");

        long fileSize = 2_000_000_000L;
        String pattern = "абра";
        long expected = fileSize - pattern.getBytes(StandardCharsets.UTF_8).length;

        char[] block = new char[8192];
        Arrays.fill(block, 'a');

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (long i = 0; i < expected / block.length; i++) {
                writer.write(block);
            }
            writer.write(block, 0, (int) (expected % block.length));
            writer.write(pattern);
        }

        assertEquals(fileSize, Files.size(file));
        assertTrue(fileSize > Runtime.getRuntime().maxMemory());

        SubstringFinder finder = new SubstringFinder();
        List<Integer> positions = finder.find(file.toString(), pattern);

        assertEquals(List.of((int) expected), positions);
    }
}