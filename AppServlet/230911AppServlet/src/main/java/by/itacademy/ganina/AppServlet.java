package by.itacademy.ganina;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AppServlet extends HttpServlet {

    List<Transport> transportList = new ArrayList<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        Transport transport = new Transport("auto", "bmw");
        Transport transport1 = new Transport("auto", "audi");
        transportList.add(transport);
        transportList.add(transport1);

    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (final PrintWriter writer = response.getWriter()) {
            writer.println("<h1>Hello AppServlet</h1>");
            for (Transport t : transportList) {
                writer.println("<h2>" + t + "</h2>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (final PrintWriter writer = response.getWriter()) {
            for (Transport t : transportList) {
                writer.println("<h2>" + t + "</h2>");
            }
        }
    }
}
