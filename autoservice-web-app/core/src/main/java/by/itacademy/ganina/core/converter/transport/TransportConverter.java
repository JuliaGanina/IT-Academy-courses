package by.itacademy.ganina.core.converter.transport;

import by.itacademy.ganina.web.model.Transport;

public interface TransportConverter {

    Transport convertLinesToTransport(String line) throws TransportConverterException;
}
