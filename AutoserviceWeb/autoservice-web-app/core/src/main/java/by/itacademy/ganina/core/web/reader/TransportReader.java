package by.itacademy.ganina.core.web.reader;

import by.itacademy.ganina.core.web.converter.transport_converter.TransportConverterException;
import by.itacademy.ganina.core.web.converter.web_converter.WebContentConverterException;
import by.itacademy.ganina.web.model.Transport;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface TransportReader {

    Map<Boolean, List<Transport>> read(final InputStream in) throws TransportReaderException, WebContentConverterException, TransportConverterException;
}
