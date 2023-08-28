package by.itacademy.ganina;

import by.itacademy.ganina.reader.DocumentReader;
import by.itacademy.ganina.reader.impl.CommonDocumentReader;
import by.itacademy.ganina.sorting.SortingReader;
import by.itacademy.ganina.sorting.impl.ConsoleSortingReader;
import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.utils.Documents;
import by.itacademy.ganina.writer.impl.CommonDocumentWriter;
import by.itacademy.ganina.writer.DocumentWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AutoserviceApplication {
    public static void main(String[] args) {
        try {
            final DocumentReader documentReader = new CommonDocumentReader();
            final List<Transport> transportList = documentReader.readFile(Documents.TRANSPORT_FILE);

            final List<Transport> invalidTransportList = new ArrayList<>();
            final List<Transport> validTransportList = new ArrayList<>();
            for (Transport transport : transportList) {
                if (!transport.isValid()) {
                    invalidTransportList.add(transport);
                } else {
                    validTransportList.add(transport);
                }
            }

            final SortingReader sortingReader = new ConsoleSortingReader();
            final Comparator<Transport> comparator = sortingReader.readSorting();
            if (comparator != null) {
                validTransportList.sort(comparator);
            }

            final DocumentWriter fileWriter = new CommonDocumentWriter();
            fileWriter.writeIntoFile(Documents.INVALID_TRANSPORT_FILE, invalidTransportList);
            fileWriter.writeIntoFile(Documents.PROCESSED_TRANSPORT_FILE, validTransportList);
        } catch (final Exception ex) {
            System.err.println("Ошибка работы программы. " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}