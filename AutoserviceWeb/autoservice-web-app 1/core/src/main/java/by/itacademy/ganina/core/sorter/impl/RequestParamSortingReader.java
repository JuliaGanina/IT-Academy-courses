package by.itacademy.ganina.core.sorter.impl;

import by.itacademy.ganina.core.sorter.SortingReader;
import by.itacademy.ganina.core.sorter.SortingReaderException;
import by.itacademy.ganina.core.sorter.SortingType;
import by.itacademy.ganina.web.model.Transport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RequestParamSortingReader implements SortingReader {

    @Override
    public Comparator<Transport> readSorting(final String requestParamLine) throws SortingReaderException {
        if (requestParamLine == null) {
            return null;
        }
        try {
            String[] sortingCommand = requestParamLine.split(", ");
            List<String> commandsList = new ArrayList<>();
            for (String command : sortingCommand) {
                commandsList.add(command);
            }
            return chooseComparator(commandsList);
        } catch (final IllegalArgumentException ex) {
            throw new SortingReaderException("ошибка чтения сортировки из request parameters", ex);
        }
    }

    public static Comparator<Transport> chooseComparator(final List<String> commandsList) {
        Comparator<Transport> comparator = null;
        if (commandsList == null) {
            return comparator;
        }

        final List<Comparator<Transport>> comparatorList = new ArrayList<>();

        for (final String commandLine : commandsList) {
            final String[] splitedCommandLine = commandLine.split("-");

            comparator = SortingType.valueOf(splitedCommandLine[0]).getTransportComparator();

            switch (splitedCommandLine[1]) {
                case "H":
                    comparatorList.add(comparator);
                    break;
                case "L":
                    comparatorList.add(comparator.reversed());
                    break;
                default:
                    comparator = null;
                    System.out.println("you don't choose sorting by" + SortingType.valueOf(splitedCommandLine[0]));
            }
        }
        System.out.println("you choose next sorting:  " + commandsList);

        switch (comparatorList.size()) {
            case 1: //выбрана сортировка по 1 параметру
                comparator = comparatorList.get(0);
                break;
            case 2: //выбрана сортировка по 2 параметрам
                comparator = comparatorList.get(0).thenComparing(comparatorList.get(1));
                break;
            default:
                return null;
        }
        return comparator;
    }
}
