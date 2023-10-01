package by.itacademy.ganina.core.reader.json;

import by.itacademy.ganina.core.converter.transport.TransportConverterException;
import by.itacademy.ganina.core.reader.TransportReaderException;
import by.itacademy.ganina.web.model.Transport;
import by.itacademy.ganina.web.model.TransportType;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.*;

import static by.itacademy.ganina.util.CommonConstance.DEFAULT_CHARSET;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonTransportReaderTest {

    @Test
    void testRead_validTransportList_happyPath() throws TransportConverterException, TransportReaderException {
        //given
        final Transport transport = new Transport(TransportType.AUTOMOBILE, "Audi", 20);
        final List<Transport> expectedTransport = Collections.singletonList(transport);
        final byte[] expectedContent = "[{\"type\":\"automobile\",\"model\":\"Audi\"}]".getBytes(DEFAULT_CHARSET);
        final ByteArrayInputStream in = new ByteArrayInputStream(expectedContent);

        //when
        final JsonTransportReader reader = new JsonTransportReader();
        final Map<Boolean, List<Transport>> transportMap = reader.read(in);
        final List<Transport> actualTransports = transportMap.get(true);

        //then
        assertEquals(expectedTransport, actualTransports);
    }

    @Test
    void testRead_invalidTransportList_happyPath() throws TransportConverterException, TransportReaderException {
        //given
        final Transport transport = new Transport(TransportType.AUTOMOBILE, "Audi!", null);
        final List<Transport> expectedTransport = Collections.singletonList(transport);
        final byte[] expectedContent = "[{\"type\":\"automobile\",\"model\":\"Audi!\"}]".getBytes(DEFAULT_CHARSET);
        final ByteArrayInputStream in = new ByteArrayInputStream(expectedContent);

        //when
        final JsonTransportReader reader = new JsonTransportReader();
        final Map<Boolean, List<Transport>> transportMap = reader.read(in);
        final List<Transport> actualTransports = transportMap.get(false);

        //then
        assertEquals(expectedTransport, actualTransports);
    }

    @Test
    void testRead_inputStreamIsNull_happyPath() throws TransportConverterException, TransportReaderException {
        //when
        final JsonTransportReader reader = new JsonTransportReader();
        final Map<Boolean, List<Transport>> actualTransportMap = reader.read(null);

        //then
        assertEquals(Collections.EMPTY_MAP, actualTransportMap);
    }


}