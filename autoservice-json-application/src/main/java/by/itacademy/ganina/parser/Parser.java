package by.itacademy.ganina.parser;

import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.validator.processor.ValidationProcessorException;

public interface Parser {

   Transport splitLines(final String line) throws ParserException, ValidationProcessorException;
}
