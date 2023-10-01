package by.itacademy.ganina.utils;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Documents {

    private static String formatDocumentForReading = "";
    private static String formatDocumentForWriting = "";

    static {
        try {
            Scanner scanner = new Scanner(System.in);
            while (!(formatDocumentForReading.equals("json") || formatDocumentForReading.equals("txt"))) {
                System.out.println("What format the document will be used for READING? Please, enter 'json' or 'txt'");
                formatDocumentForReading = scanner.nextLine();
            }
            while (!(formatDocumentForWriting.equals("json") || formatDocumentForWriting.equals("txt"))) {
                System.out.println("What format the document will be used for WRITING? Please, enter 'json' or 'txt'");
                formatDocumentForWriting = scanner.nextLine();
            }
        } catch (final Exception ex) {
            throw new NoSuchElementException("No line found");
        }
    }

    public final static File TRANSPORT_FILE = new File(String.format(".\\src\\main\\resources\\%s.documents\\transport.%s", formatDocumentForReading, formatDocumentForReading));
    public final static File INVALID_TRANSPORT_FILE = new File(String.format(".\\src\\main\\resources\\%s.documents\\invalid-transport.%s", formatDocumentForWriting, formatDocumentForWriting));
    public final static File PROCESSED_TRANSPORT_FILE = new File(String.format(".\\src\\main\\resources\\%s.documents\\processed-transport.%s", formatDocumentForWriting, formatDocumentForWriting));

    private Documents() {
    }

    public static String getFormatDocumentForReading() {
        return formatDocumentForReading;
    }

    public static String getFormatDocumentForWriting() {
        return formatDocumentForWriting;
    }
}
