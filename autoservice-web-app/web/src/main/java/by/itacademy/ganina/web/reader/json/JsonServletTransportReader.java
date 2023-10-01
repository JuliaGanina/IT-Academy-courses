package by.itacademy.ganina.web.reader.json;

import by.itacademy.ganina.core.converter.transport.TransportConverterException;
import by.itacademy.ganina.core.reader.TransportReader;
import by.itacademy.ganina.core.reader.TransportReaderException;
import by.itacademy.ganina.core.reader.json.JsonTransportReader;
import by.itacademy.ganina.web.model.Transport;

import java.io.*;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import static by.itacademy.ganina.util.CommonConstance.DEFAULT_CHARSET;

public class JsonServletTransportReader implements TransportReader {

    private final static TransportReader READER = new JsonTransportReader();

    @Override
    public Map<Boolean, List<Transport>> read(final InputStream in) throws TransportReaderException, TransportConverterException {
        try {
            final InputStream jsonIN = getDecodedStream(in);
            return READER.read(jsonIN);
        } catch (final IOException ex) {
            throw new TransportReaderException("ошибка обработки конвертации http request  в Транспорт", ex);
        }
    }

    private static InputStream getDecodedStream(final InputStream in) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET))) {
            final String content = reader.lines().reduce("", String::concat);
            final String encodedContent = content.split("=")[1];

            final String jsonContent = URLDecoder.decode(encodedContent, DEFAULT_CHARSET);
            return new ByteArrayInputStream(jsonContent.getBytes(DEFAULT_CHARSET));
        }
    }
}
