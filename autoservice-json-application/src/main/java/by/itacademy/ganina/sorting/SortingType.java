package by.itacademy.ganina.sorting;

import by.itacademy.ganina.transport.Transport;

import java.util.Comparator;
import java.util.function.Function;

public enum SortingType {
    TYPE(Transport::getType),
    MODEL(Transport::getModel),
    PRICE(Transport::getTax);

    private final Comparator<Transport> transportComparator;

    <T extends Comparable<T>> SortingType(final Function<Transport, T> function) {
        this.transportComparator = Comparator.comparing(function);
    }

    public Comparator<Transport> getTransportComparator() {
        return transportComparator;
    }
}
