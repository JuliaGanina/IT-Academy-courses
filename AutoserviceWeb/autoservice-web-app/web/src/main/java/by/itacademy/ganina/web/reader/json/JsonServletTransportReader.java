package by.itacademy.ganina.web.reader.json;

import by.itacademy.ganina.core.web.converter.transport_converter.TransportConverterException;
import by.itacademy.ganina.core.web.converter.web_converter.WebContentConverterException;
import by.itacademy.ganina.core.web.reader.TransportReader;
import by.itacademy.ganina.core.web.reader.TransportReaderException;
import by.itacademy.ganina.core.web.reader.json.JsonTransportReader;
import by.itacademy.ganina.web.model.Transport;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class JsonServletTransportReader implements TransportReader {

    private final static TransportReader READER = new JsonTransportReader();

    @Override
    public Map<Boolean, List<Transport>> read(final InputStream in) throws TransportReaderException, WebContentConverterException, TransportConverterException {
        try {
            final InputStream jsonIN = getDecodedStream(in);
            return READER.read(jsonIN);
        } catch (final IOException ex) {
            throw new TransportReaderException("ошибка обработки конвертации http request  в Транспорт", ex);
        }
    }

    private static InputStream getDecodedStream(final InputStream in) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            final String content = reader.lines().reduce("", String::concat);
            final String encodedContent = content.split("=")[1];

            final String jsonContent = URLDecoder.decode(encodedContent, StandardCharsets.UTF_8);
            return new ByteArrayInputStream(jsonContent.getBytes(StandardCharsets.UTF_8));
        }
    }
}
