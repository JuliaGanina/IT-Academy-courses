package by.itacademy.ganina.utils;

import by.itacademy.ganina.reader.DocumentReader;
import by.itacademy.ganina.reader.DocumentReaderException;
import by.itacademy.ganina.reader.impl.JsonDocumentReader;
import by.itacademy.ganina.reader.impl.TextDocumentReader;
import by.itacademy.ganina.transport.ServiceTax;
import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.writer.DocumentWriter;
import by.itacademy.ganina.writer.DocumentWriterException;
import by.itacademy.ganina.writer.impl.JsonDocumentWriter;
import by.itacademy.ganina.writer.impl.TextDocumentWriter;

import java.util.HashMap;
import java.util.Map;


import java.util.function.Predicate;
import java.util.regex.Pattern;

public class DocumentAdapterAndValidator {

    private static final Predicate<String> MODEL_VALIDATOR = Pattern.compile("^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$").asPredicate();
    private static final Predicate<String> TYPE_VALIDATOR = Pattern.compile("автомобиль|мотоцикл|микроавтобус").asPredicate();

    private static final Map<String, DocumentReader> READER_MAP = new HashMap<>();
    private static final Map<String, DocumentWriter> WRITER_MAP = new HashMap<>();

    static {
        READER_MAP.put(DocumentType.TXT.toString().toLowerCase(), new TextDocumentReader());
        READER_MAP.put(DocumentType.JSON.toString().toLowerCase(), new JsonDocumentReader());

        WRITER_MAP.put(DocumentType.TXT.toString().toLowerCase(), new TextDocumentWriter());
        WRITER_MAP.put(DocumentType.JSON.toString().toLowerCase(), new JsonDocumentWriter());
    }

    public DocumentReader getReader(String formatDocumentForReading) throws DocumentReaderException {
        try {
            return READER_MAP.get(formatDocumentForReading);
        } catch (final IllegalArgumentException ex) {
            throw new DocumentReaderException("Ошибка определения типа документа", ex);
        }
    }

    public DocumentWriter getWriter(String formatDocumentForWriting) throws DocumentWriterException {
        try {
            return WRITER_MAP.get(formatDocumentForWriting);
        } catch (final IllegalArgumentException ex) {
            throw new DocumentWriterException("Ошибка определения типа документа", ex);
        }
    }

    public static Transport splitLines(String line) {
        final String[] splitedLine = line.split(",\\s");
        final String type = splitedLine[0];
        final String model = splitedLine[1];
        Integer tax = 0;
        final boolean isValid = (TYPE_VALIDATOR.test(type) && MODEL_VALIDATOR.test(model));

        if (isValid) {
            switch (type) {
                case "автомобиль":
                    tax = ServiceTax.AUTOMOBILE.getTax();
                    break;
                case "мотоцикл":
                    tax = ServiceTax.MOTOBIKE.getTax();
                    break;
                case "микроавтобус":
                    tax = ServiceTax.MICROAUTOBUS.getTax();
                    break;
                default:
                    tax = 0;
            }
        }
        return new Transport(type, model, tax, isValid);
    }
}
