package by.itacademy.ganina.adapter;

import by.itacademy.ganina.reader.DocumentReader;
import by.itacademy.ganina.writer.DocumentWriter;

public interface AdapterAndValidator {

    DocumentReader getReader(String formatDocumentForReading) throws AdapterAndValidatorException;

    DocumentWriter getWriter(String formatDocumentForWriting) throws AdapterAndValidatorException;


}
