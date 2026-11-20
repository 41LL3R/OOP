package ru.nsu.etronin.task131;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Класс для потокового чтения файла блоками.
 */

public class FileChunkReader implements AutoCloseable {

    /**
     * Интерфейс для обработки прочитанных блоков.
     */

    public interface ChunkHandler {

        void onChunk(char[] buffer, int length, long baseOffset);
    }

    private final BufferedReader reader;
    private final char[] buffer;
    private final int overlap;
    private final int chunkSize;

    /**
     * Конструктор класса.
     *
     * @param filePath  путь к файлу
     * @param overlap   количество символов для перекрытия
     * @param chunkSize размер читаемого блока
     * @throws IOException если файл не найден
     */

    public FileChunkReader(String filePath, int overlap, int chunkSize) throws IOException {
        this.reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        this.overlap = Math.max(0, overlap);
        this.chunkSize = chunkSize;
        this.buffer = new char[chunkSize + overlap];
    }

    /**
     * Читает файл и передает блоки в обработчик.
     *
     * @param handler обработчик блоков
     * @throws IOException при ошибке чтения
     */

    public void forEachChunk(ChunkHandler handler) throws IOException {
        int carryOver = 0;
        long globalOffset = 0;
        int charsRead;

        while ((charsRead = reader.read(buffer, carryOver, chunkSize)) != -1) {
            int totalValid = carryOver + charsRead;
            long chunkStartOffset = globalOffset - carryOver;

            handler.onChunk(buffer, totalValid, chunkStartOffset);

            if (overlap > 0 && totalValid >= overlap) {
                System.arraycopy(buffer, totalValid - overlap, buffer, 0, overlap);
            }
            carryOver = overlap;
            globalOffset += charsRead;
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}