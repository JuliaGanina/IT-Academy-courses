package by.itacademy.ganina.core.sorter;

import by.itacademy.ganina.web.model.Transport;

import java.util.Comparator;

public interface SortingReader {

    Comparator<Transport> readSorting(String sortingType) throws SortingReaderException;
}
