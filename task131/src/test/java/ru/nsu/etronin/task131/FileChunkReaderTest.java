package ru.nsu.etronin.task131;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FileChunkReaderTest {

    @TempDir
    Path tmp;

    private Path write(String content) throws IOException {
        Path p = tmp.resolve("f.txt");
        Files.writeString(p, content, StandardCharsets.UTF_8);
        return p;
    }

    private record Chunk(long baseOffset, String text) {}

    private List<Chunk> readAll(Path file, int overlap, int chunkSize) throws IOException {
        List<Chunk> result = new ArrayList<>();
        try (FileChunkReader r = new FileChunkReader(file.toString(), overlap, chunkSize)) {
            r.forEachChunk((buf, len, base) ->
                    result.add(new Chunk(base, new String(buf, 0, len))));
        }
        return result;
    }

    @Test
    void overlapCarriesTailAndBaseOffsetIsCorrect() {
        try {
            String content = "0123456789abcdef";
            for (Chunk c : readAll(write(content), 2, 5)) {
                int start = (int) c.baseOffset();
                assertEquals(
                        content.substring(start, start + c.text().length()),
                        c.text());
            }
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void utf8ChunkSizeIsCountedInCharsNotBytes() {
        try {
            List<Chunk> chunks = readAll(write("абракадабра"), 0, 5);
            assertEquals(3, chunks.size());
            assertEquals("абрак", chunks.get(0).text());
            assertEquals(5L, chunks.get(1).baseOffset());
            assertEquals(10L, chunks.get(2).baseOffset());
        } catch (IOException e) {
            fail(e);
        }
    }
}