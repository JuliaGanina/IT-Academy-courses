package by.itacademy.ganina.docs.impl;

import by.itacademy.ganina.reader.DocumentReader;
import by.itacademy.ganina.reader.impl.JsonDocumentReader;
import by.itacademy.ganina.reader.impl.TextDocumentReader;
import by.itacademy.ganina.transport.ServiceTax;
import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.docs.AdapterAndValidator;
import by.itacademy.ganina.docs.AdapterAndValidatorException;
import by.itacademy.ganina.docs.DocumentType;
import by.itacademy.ganina.writer.DocumentWriter;
import by.itacademy.ganina.writer.impl.JsonDocumentWriter;
import by.itacademy.ganina.writer.impl.TextDocumentWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class DocumentAdapterAndValidator implements AdapterAndValidator {

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

    @Override
    public DocumentReader getReader(String formatDocumentForReading) throws AdapterAndValidatorException {
        try {
            return READER_MAP.get(formatDocumentForReading);
        } catch (final IllegalArgumentException ex) {
            throw new AdapterAndValidatorException("Ошибка определения типа документа", ex);
        }
    }

    @Override
    public DocumentWriter getWriter(String formatDocumentForWriting) throws AdapterAndValidatorException {
        try {
            return WRITER_MAP.get(formatDocumentForWriting);
        } catch (final IllegalArgumentException ex) {
            throw new AdapterAndValidatorException("Ошибка определения типа документа", ex);
        }
    }

    public static Transport splitLines(String line) {
        final String[] splitedLine = line.split(",\\s");
        final String type = splitedLine[0];
        final String model = splitedLine[1];
        Integer tax = 0; //по коду она не может быть final
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
