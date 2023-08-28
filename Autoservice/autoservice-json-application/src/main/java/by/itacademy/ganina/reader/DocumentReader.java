package by.itacademy.ganina.reader;

import by.itacademy.ganina.transport.Transport;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DocumentReader {

    List<Transport> readFile(File fileToRead) throws DocumentReaderException, IOException;
}
