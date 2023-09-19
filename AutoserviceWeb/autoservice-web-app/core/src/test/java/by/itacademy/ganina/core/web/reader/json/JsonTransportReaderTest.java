package by.itacademy.ganina.core.web.reader.json;

import by.itacademy.ganina.core.web.converter.transport_converter.TransportConverterException;
import by.itacademy.ganina.core.web.converter.web_converter.WebContentConverterException;
import by.itacademy.ganina.core.web.reader.TransportReaderException;
import by.itacademy.ganina.web.model.Transport;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonTransportReaderTest {

    @Test
    void testRead_validTransportList_happyPath() throws TransportConverterException, WebContentConverterException, TransportReaderException {
        //given
        final Transport transport = new Transport("автомобиль", "Audi", 20);
        final List<Transport> expectedTransport = Collections.singletonList(transport);
        final byte[] expectedContent = "[{\"type\":\"автомобиль\",\"model\":\"Audi\"}]".getBytes(StandardCharsets.UTF_8);
        final ByteArrayInputStream in = new ByteArrayInputStream(expectedContent);

        //when
        final JsonTransportReader reader = new JsonTransportReader();
        final Map<Boolean, List<Transport>> transportMap = reader.read(in);
        final List<Transport> actualTransports = transportMap.get(true);

        //then
        assertEquals(expectedTransport, actualTransports);
    }

    @Test
    void testRead_invalidTransportList_happyPath() throws TransportConverterException, WebContentConverterException, TransportReaderException {
        //given
        final Transport transport = new Transport("автомобиль", "Audi!", null);
        final List<Transport> expectedTransport = Collections.singletonList(transport);
        final byte[] expectedContent = "[{\"type\":\"автомобиль\",\"model\":\"Audi!\"}]".getBytes(StandardCharsets.UTF_8);
        final ByteArrayInputStream in = new ByteArrayInputStream(expectedContent);

        //when
        final JsonTransportReader reader = new JsonTransportReader();
        final Map<Boolean, List<Transport>> transportMap = reader.read(in);
        final List<Transport> actualTransports = transportMap.get(false);

        //then
        assertEquals(expectedTransport, actualTransports);
    }

    @Test
    void testRead_inputStreamIsNull_happyPath() throws TransportConverterException, WebContentConverterException, TransportReaderException {
        //when
        final JsonTransportReader reader = new JsonTransportReader();
        final Map<Boolean, List<Transport>> actualTransportMap = reader.read(null);

        //then
        assertEquals(Collections.EMPTY_MAP, actualTransportMap);
    }
}