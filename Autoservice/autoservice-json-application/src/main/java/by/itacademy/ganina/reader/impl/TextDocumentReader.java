package by.itacademy.ganina.reader.impl;

import by.itacademy.ganina.reader.DocumentReader;
import by.itacademy.ganina.reader.DocumentReaderException;
import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.docs.impl.DocumentAdapterAndValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TextDocumentReader implements DocumentReader {

    @Override
    public List<Transport> readFile(File fileToRead) throws DocumentReaderException {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileToRead, StandardCharsets.UTF_8))) {
            return reader.lines()
                    .map(DocumentAdapterAndValidator::splitLines)
                    .toList();
        } catch (final IOException ex) {
            throw new DocumentReaderException("Ошибка при чтении файла " + fileToRead, ex);
        } catch (final IllegalArgumentException ex) {
            throw new DocumentReaderException("Ошибка определения типа транспорта", ex);
        } catch (final RuntimeException ex) {
            throw new DocumentReaderException("Ошибка анализа прочтенного файла", ex);
        }
    }
}
