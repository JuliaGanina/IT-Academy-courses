package by.itacademy.ganina.writer;

import by.itacademy.ganina.transport.Transport;

import java.io.File;
import java.util.List;

public interface DocumentWriter {

    void writeIntoFile(File fileToWrite, List<Transport> transportList) throws DocumentWriterException;

}
