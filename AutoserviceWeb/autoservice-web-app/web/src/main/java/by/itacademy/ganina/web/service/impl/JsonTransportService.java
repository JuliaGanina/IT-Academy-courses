package by.itacademy.ganina.web.service.impl;

import by.itacademy.ganina.core.web.converter.transport_converter.TransportConverterException;
import by.itacademy.ganina.core.web.converter.web_converter.WebContentConverterException;
import by.itacademy.ganina.core.web.reader.TransportReader;
import by.itacademy.ganina.core.web.reader.TransportReaderException;
import by.itacademy.ganina.web.model.Transport;
import by.itacademy.ganina.web.reader.json.JsonServletTransportReader;
import by.itacademy.ganina.web.service.TransportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonTransportService implements TransportService {
    @Override
    public void processTransport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final TransportReader reader = new JsonServletTransportReader();

        try {
            final Map<Boolean, List<Transport>> transportMap = reader.read(request.getInputStream());

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(transportMap.get(true));
            response.getWriter().println(transportMap.get(false));

        } catch (final TransportReaderException ex) {
            throw new IOException("ошибка обработки контента", ex);
        } catch (final TransportConverterException ex) {
            throw new RuntimeException("ошибка конвертации строки в объект Транспорта", ex);
        } catch (final WebContentConverterException ex) {
            throw new RuntimeException("ошибка конвертации контента в строку", ex);
        }
    }
}
