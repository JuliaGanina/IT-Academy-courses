package by.itacademy.ganina.reader.impl;

import by.itacademy.ganina.reader.DocumentReader;
import by.itacademy.ganina.reader.DocumentReaderException;
import by.itacademy.ganina.reader.impl.impl.JsonDocumentReader;
import by.itacademy.ganina.reader.impl.impl.TextDocumentReader;
import by.itacademy.ganina.transport.ServiceTax;
import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.utils.Documents;
import by.itacademy.ganina.writer.impl.impl.JsonDocumentWriter;
import by.itacademy.ganina.writer.impl.impl.TextDocumentWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class CommonDocumentReader implements DocumentReader {

    private static final Predicate<String> MODEL_VALIDATOR = Pattern.compile("^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$").asPredicate();
    private static final Predicate<String> TYPE_VALIDATOR = Pattern.compile("автомобиль|мотоцикл|микроавтобус").asPredicate();

    @Override
    public List<Transport> readFile(File fileToRead) throws DocumentReaderException {
        switch (Documents.getFormatDocumentForReading()) {
            case "json":
                return new JsonDocumentReader().readFile(fileToRead);
            case "txt":
                return new TextDocumentReader().readFile(fileToRead);
            default:
                return null;
        }
    }

    protected static Transport splitLines(String line) {
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
                    break;
            }
        }
        return new Transport(type, model, tax, isValid);
    }
}