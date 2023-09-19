package by.itacademy.ganina.core.web.converter.transport_converter;

import by.itacademy.ganina.web.model.Transport;

public interface TransportConverter {

    Transport convertLinesToTransport(String line) throws TransportConverterException;
}
