package by.itacademy.ganina.core.web.converter.transport_converter.impl;

import by.itacademy.ganina.core.web.converter.transport_converter.TransportConverter;
import by.itacademy.ganina.core.web.converter.transport_converter.TransportConverterException;
import by.itacademy.ganina.web.model.ServiceTax;
import by.itacademy.ganina.web.model.Transport;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class TransportConverterImpl implements TransportConverter {

    private static final Predicate<String> MODEL_VALIDATOR = Pattern.compile("^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$").asPredicate();
    private static final Predicate<String> TYPE_VALIDATOR = Pattern.compile("автомобиль|мотоцикл|микроавтобус").asPredicate();

    @Override
    public Transport convertLinesToTransport(final String line) throws TransportConverterException {
        try {
            final String[] splitedLine = line.split(",\\s");
            final String type = splitedLine[0];
            final String model = splitedLine[1];

            final boolean isValid = (TYPE_VALIDATOR.test(type) && MODEL_VALIDATOR.test(model));

            Transport transport = null;

            if (isValid) {
                switch (type) {
                    case "автомобиль":
                        transport = new Transport(type, model, ServiceTax.AUTOMOBILE.getTax());
                        break;
                    case "мотоцикл":
                        transport = new Transport(type, model, ServiceTax.MOTOBIKE.getTax());
                        break;
                    case "микроавтобус":
                        transport = new Transport(type, model, ServiceTax.MICROAUTOBUS.getTax());
                        break;
                    default:
                        transport = new Transport(type, model, null);
                }
            } else {
                return new Transport(type, model, null);
            }
            return transport;
        } catch (final IllegalArgumentException ex) {
            throw new TransportConverterException("Ошибка определения типа транспорта", ex);
        } catch (final RuntimeException ex) {
            throw new TransportConverterException("Ошибка анализа прочтенного контента", ex);
        }
    }
}
