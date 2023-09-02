package by.itacademy.ganina.sorting;

import by.itacademy.ganina.transport.Transport;

import java.util.Comparator;

public interface SortingReader {

    Comparator<Transport> readSorting() throws SortingReaderException;
}
