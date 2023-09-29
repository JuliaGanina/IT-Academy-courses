package by.itacademy.ganina.core.reader;

import by.itacademy.ganina.core.converter.transport.TransportConverterException;
import by.itacademy.ganina.web.model.Transport;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface TransportReader {

    Map<Boolean, List<Transport>> read(InputStream in) throws TransportReaderException, TransportConverterException;
}
