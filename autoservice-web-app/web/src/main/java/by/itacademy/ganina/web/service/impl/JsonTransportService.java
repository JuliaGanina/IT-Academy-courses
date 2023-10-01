package by.itacademy.ganina.web.service.impl;

import by.itacademy.ganina.core.converter.transport.TransportConverterException;
import by.itacademy.ganina.core.reader.TransportReader;
import by.itacademy.ganina.core.reader.TransportReaderException;
import by.itacademy.ganina.core.sorter.SortingReader;
import by.itacademy.ganina.core.sorter.SortingReaderException;
import by.itacademy.ganina.core.sorter.impl.RequestParamSortingReader;
import by.itacademy.ganina.core.writer.TransportWriter;
import by.itacademy.ganina.core.writer.TransportWriterException;
import by.itacademy.ganina.web.model.Transport;
import by.itacademy.ganina.web.reader.json.JsonServletTransportReader;
import by.itacademy.ganina.web.service.TransportService;
import by.itacademy.ganina.web.writer.ServletTransportWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class JsonTransportService implements TransportService {
    @Override
    public void processTransport(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final TransportReader reader = new JsonServletTransportReader();
        final SortingReader sortingReader = new RequestParamSortingReader();

        String requestParameters = null;

        try {
            final Map<Boolean, List<Transport>> transportMap = reader.read(request.getInputStream());

            requestParameters = request.getParameterMap()
                    .entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "-" + entry.getValue()[0] + ", ")
                    .reduce("", String::concat);

         //   final Comparator<Transport> comparator = sortingReader.readSorting(requestParameters);

            response.setContentType("text/html;charset=UTF-8");
         //   if (comparator != null) {
         //     transportMap.get(true).sort(comparator);
         //   }
            System.out.println(requestParameters);
            System.out.println(1);
            final TransportWriter transportWriter = new ServletTransportWriter();
            transportWriter.write(transportMap.get(true), response.getWriter());
            transportWriter.write(transportMap.get(false), response.getWriter());

        } catch (final TransportReaderException ex) {
            throw new IOException("ошибка обработки контента", ex);
        } catch (final TransportConverterException ex) {
            throw new RuntimeException("ошибка конвертации строки в объект Транспорта", ex);
        } catch (final TransportWriterException ex) {
            throw new RuntimeException("невозможно записать объект в http", ex);
      //  } catch (SortingReaderException ex) {
       //     throw new RuntimeException("ошибка чтения сортировки", ex);
        }
    }
}
