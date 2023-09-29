package by.itacademy.ganina.core.writer;

import by.itacademy.ganina.web.model.Transport;

import java.io.PrintWriter;
import java.util.List;

public interface TransportWriter {

    void write(List<Transport> transportList,PrintWriter writer) throws TransportWriterException;
}
