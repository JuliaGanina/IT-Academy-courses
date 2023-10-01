package by.itacademy.ganina.adapter.impl;

import by.itacademy.ganina.reader.DocumentReader;
import by.itacademy.ganina.reader.impl.JsonDocumentReader;
import by.itacademy.ganina.reader.impl.TextDocumentReader;
import by.itacademy.ganina.adapter.AdapterAndValidator;
import by.itacademy.ganina.adapter.AdapterAndValidatorException;
import by.itacademy.ganina.utils.DocumentType;
import by.itacademy.ganina.writer.DocumentWriter;
import by.itacademy.ganina.writer.impl.JsonDocumentWriter;
import by.itacademy.ganina.writer.impl.TextDocumentWriter;

import java.util.HashMap;
import java.util.Map;

public class DocumentAdapterAndValidator implements AdapterAndValidator {

    private static final Map<String, DocumentReader> READER_MAP = new HashMap<>();
    private static final Map<String, DocumentWriter> WRITER_MAP = new HashMap<>();

    static {
        READER_MAP.put(DocumentType.TXT.toString().toLowerCase(), new TextDocumentReader());
        READER_MAP.put(DocumentType.JSON.toString().toLowerCase(), new JsonDocumentReader());

        WRITER_MAP.put(DocumentType.TXT.toString().toLowerCase(), new TextDocumentWriter());
        WRITER_MAP.put(DocumentType.JSON.toString().toLowerCase(), new JsonDocumentWriter());
    }

    @Override
    public DocumentReader getReader(final String formatDocumentForReading) throws AdapterAndValidatorException {
        try {
            return READER_MAP.get(formatDocumentForReading);
        } catch (final IllegalArgumentException ex) {
            throw new AdapterAndValidatorException("Ошибка определения типа документа", ex);
        }
    }

    @Override
    public DocumentWriter getWriter(final String formatDocumentForWriting) throws AdapterAndValidatorException {
        try {
            return WRITER_MAP.get(formatDocumentForWriting);
        } catch (final IllegalArgumentException ex) {
            throw new AdapterAndValidatorException("Ошибка определения типа документа", ex);
        }
    }
}
