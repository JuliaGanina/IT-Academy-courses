package by.itacademy.ganina.writer.impl;

import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.utils.Documents;
import by.itacademy.ganina.writer.DocumentWriter;
import by.itacademy.ganina.writer.DocumentWriterException;
import by.itacademy.ganina.writer.impl.impl.JsonDocumentWriter;
import by.itacademy.ganina.writer.impl.impl.TextDocumentWriter;

import java.io.File;
import java.util.List;

public class CommonDocumentWriter implements DocumentWriter {

    @Override
    public void writeIntoFile(File fileToWrite, List<Transport> transportList) throws DocumentWriterException {
        switch (Documents.getFormatDocumentForWriting()) {
            case "json":
                new JsonDocumentWriter().writeIntoFile(fileToWrite, transportList);
                break;
            case "txt":
                new TextDocumentWriter().writeIntoFile(fileToWrite, transportList);
                break;
            default:
                System.out.println(" в метод writeIntoFile  отправлен некорректный формат");
                break;
        }
    }
}
