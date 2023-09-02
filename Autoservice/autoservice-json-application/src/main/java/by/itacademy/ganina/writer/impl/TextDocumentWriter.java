package by.itacademy.ganina.writer.impl;

import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.writer.DocumentWriter;
import by.itacademy.ganina.writer.DocumentWriterException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class TextDocumentWriter implements DocumentWriter {

    @Override
    public void writeIntoFile(File fileToWrite, List<Transport> transportList) throws DocumentWriterException {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite, StandardCharsets.UTF_8))) {
            for (final Transport value : transportList) {
                bufferedWriter.write(value.toString() + "\n");
            }
            System.out.println(fileToWrite.getName() + " written to fileToWrite with path:" + fileToWrite.getAbsolutePath());
        } catch (final IOException ex) {
            throw new DocumentWriterException("Ошибка при записи файла " + fileToWrite.getAbsolutePath(), ex);
        }
    }
}
