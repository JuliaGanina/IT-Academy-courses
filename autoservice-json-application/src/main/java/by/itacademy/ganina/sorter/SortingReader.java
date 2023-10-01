package by.itacademy.ganina.sorter;

import by.itacademy.ganina.transport.Transport;

import java.util.Comparator;

public interface SortingReader {

    Comparator<Transport> readSorting() throws SortingReaderException;
}
