package by.itacademy.ganina.core.converter.transport.impl;

import by.itacademy.ganina.core.converter.transport.TransportConverter;
import by.itacademy.ganina.core.converter.transport.TransportConverterException;
import by.itacademy.ganina.web.model.TransportType;
import by.itacademy.ganina.web.model.Transport;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class TransportConverterImpl implements TransportConverter {

    private static final Predicate<String> MODEL_VALIDATOR = Pattern.compile("^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$").asPredicate();
    private static final Predicate<String> TYPE_VALIDATOR = Pattern.compile("^(automobile|motobike|microautobus)").asPredicate();

    @Override
    public Transport convertLinesToTransport(final String line) throws TransportConverterException {
        try {
            final String[] splitedLine = line.split(",\\s");
            final String type = splitedLine[0];
            final String model = splitedLine[1];

            final boolean isValid = (TYPE_VALIDATOR.test(type) && MODEL_VALIDATOR.test(model));

            if (isValid) {
               return new Transport(TransportType.valueOf(type.toUpperCase()),
                                          model,
                                          TransportType.valueOf(type.toUpperCase()).getPrice());
            } else {
                return new Transport(TransportType.valueOf(type.toUpperCase()),
                                          model,
                                          null);
            }
        } catch (final IllegalArgumentException ex) {
            throw new TransportConverterException("Ошибка определения типа транспорта", ex);
        }
    }
}
