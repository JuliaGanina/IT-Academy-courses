package by.itacademy.ganina.web.writer;

import by.itacademy.ganina.core.writer.TransportWriter;
import by.itacademy.ganina.core.writer.TransportWriterException;
import by.itacademy.ganina.web.model.Transport;


import java.io.PrintWriter;
import java.util.List;

public class ServletTransportWriter implements TransportWriter {

    @Override
    public void write(final List<Transport> transportList, final PrintWriter writer) throws TransportWriterException {
        final Integer price = transportList.get(0).getPrice();  //!null, если транспорт валидный
        try {
            writer.println("<h1>" + (price != null
                    ? "Processed transports"
                    : "Invalid transports") +
                    "</h1>");
            writer.println("<table border=\"1\">");
            writer.println("<tr>" +
                    "<th>Тип</th>" +
                    "<th>Модель</th>" +
                    (price != null ? "<th>Цена</th>" : "") +
                    "</tr>");

            for (Transport t : transportList) {
                writer.println("<tr>" +
                        "<td>" + t.getTransportType().name().toLowerCase() + "</td>" +
                        "<td>" + t.getModel() + "</td>" +
                        (price != null ? "<td>" + t.getPrice() + "</td>" : "") +
                        "</tr>");
            }
            writer.println("</table>");
        } catch (final Exception ex) {
            throw new TransportWriterException("ошибка записи транспарта в http", ex);
        }
    }
}
