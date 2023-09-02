package by.itacademy.ganina.sorting.impl;

import by.itacademy.ganina.sorting.SortingReader;
import by.itacademy.ganina.sorting.SortingReaderException;
import by.itacademy.ganina.sorting.SortingType;
import by.itacademy.ganina.transport.Transport;

import java.util.*;

public class ConsoleSortingReader implements SortingReader {

    @Override
    public Comparator<Transport> readSorting() throws SortingReaderException {
        final List<String> commands = new ArrayList<>();

        try (Scanner scannerIn = new Scanner(System.in)) {
            System.out.println(("press: 'y' , if you want to sort lines in file, or 'stop' to see result now"));
            String command = scannerIn.nextLine();
            while (!command.equals("stop")) {
                commands.clear();
                if (command.equals("y")) {
                    for (int index = 0; index < SortingType.values().length; index++) {
                        command = scanCommand(index, scannerIn);
                        commands.add(SortingType.values()[index] + "-" + command.toUpperCase());
                    }
                }
                System.out.println(("press: 'y' , if you want to sort lines again, or 'stop' to see result now"));
                command = scannerIn.nextLine();
            }
            return chooseComparator(commands);
        } catch (final RuntimeException ex) {
            throw new SortingReaderException("Ошибка чтения сортировки для транспорта", ex);
        }
    }

    private static boolean isCommandValid(String command) {
        return (command.equals("h") || command.equals("l") || command.equals("n"));
    }

    private static String scanCommand(int index, Scanner scanner) throws SortingReaderException {
        System.out.println("press 'n' to continue without sorting by" + SortingType.values()[index] + "\n"
                + " or press: 'h' to sort by high," + "\n"
                + "        or 'l' to sort by low ");
        try {
            String command = scanner.nextLine();
            if (!isCommandValid(command)) {
                System.out.println("your command is uncorrected, please, try again.");
                command = scanCommand(index, scanner);
            }
            return command;
        } catch (final RuntimeException ex) {
            throw new SortingReaderException("Ошибка чтения сортировки для транспорта", ex);
        }
    }

    private static Comparator<Transport> chooseComparator(List<String> commandsList) {
        Comparator<Transport> comparator = null;
        if (commandsList == null) {
            return comparator;
        }

        List<Comparator<Transport>> comparatorList = new ArrayList<>();

        for (String commandLine : commandsList) {
            String[] splitedCommandLine = commandLine.split("-");
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