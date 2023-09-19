package by.itacademy.ganina.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TransportService {

    void processTransport(final HttpServletRequest request, final HttpServletResponse response) throws IOException;
}
