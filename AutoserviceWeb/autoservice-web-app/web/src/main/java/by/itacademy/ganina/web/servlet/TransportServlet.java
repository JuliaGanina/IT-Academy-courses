package by.itacademy.ganina.web.servlet;

import by.itacademy.ganina.web.service.TransportService;
import by.itacademy.ganina.web.service.impl.JsonTransportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class TransportServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        final TransportService service = new JsonTransportService();
        service.processTransport(request, response);
    }
}
