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
     /*   String   requestParameters = request.getParameterMap()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + "-" + entry.getValue()[0] + ", ")
                .reduce("", String::concat);*/
      //  response.getWriter().println(requestParameters);
        service.processTransport(request, response);
    }
}
