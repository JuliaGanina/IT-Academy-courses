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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AppServlet extends HttpServlet {

    List<Transport> transportList = new ArrayList<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        Transport transport = new Transport("auto", "bmw");
        Transport transport1 = new Transport("auto", "audi");
        Transport transport2 = new Transport("auto", "444-!");
        transportList.add(transport);
        transportList.add(transport1);
        transportList.add(transport2);

    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String requestBody = null;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
            requestBody = reader.lines().reduce("", String::concat);
        }

        response.setContentType("text/html;charset=UTF-8");

        try (final PrintWriter writer = response.getWriter()) {
            writer.println("<h1>Hello AppServlet, doGET</h1>");
            writer.println("body :" + requestBody);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String requestBody = null; //JSON type expected
        String requestParameters = null; // expected SortingType in format key: TYPE, MODEL, PRICE; value: H, L, N

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
            requestBody = reader.lines().reduce("", String::concat);

            requestParameters = request.getParameterMap()
                    .entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "-" + entry.getValue()[0] + ", ")
                    .reduce("", String::concat);

            final List<String> commandList = new ArrayList<>();
            final String[] splitedLines = requestParameters.split(",\\s");
            int index = 0;

            for (final String sortingCommand : splitedLines) {
                if (sortingCommand != null) {
                    commandList.add(splitedLines[index]);
                    index++;
                }
            }
        }
// чтение сортировки и содержимого body  требует подключения модуля в общий проект
        //поэтому пока это лишь заготовка вывода
        response.setContentType("text/html;charset=UTF-8");

        try (final PrintWriter writer = response.getWriter()) {
            writer.println("<h1>Hello AppServlet, doPOST</h1>");
            writer.println("<table>");
            for (Transport t : transportList) {
               writer.println("<td><tr>"+t+"</td></tr>");
            }
            writer.println("</table>");
            writer.println("body :" + requestBody);
            writer.println("parameters :" + requestParameters);
        }
    }
}
