package by.itacademy.ganina.parser.impl;

import by.itacademy.ganina.parser.Parser;
import by.itacademy.ganina.parser.ParserException;
import by.itacademy.ganina.transport.ServiceTax;
import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.validator.processor.ValidationProcessorException;
import by.itacademy.ganina.validator.processor.ValidationResult;
import by.itacademy.ganina.validator.processor.impl.FieldsValidationProcessor;

public class StringLinesParser implements Parser {

    @Override
    public Transport splitLines(final String line) throws ParserException, ValidationProcessorException {
        final String[] splitedLine = line.split(",\\s");
        final String type = splitedLine[0];
        final String model = splitedLine[1];
        final Integer tax = 0;
        //прошлая версия валидации
        //  final boolean isValid = (TYPE_VALIDATOR.test(type) && MODEL_VALIDATOR.test(model));

        final Transport transport = new Transport(type, model, tax, false);
        final ValidationResult validationResult = new FieldsValidationProcessor().validate(transport);
        transport.setValid(validationResult.isValid());
        if (validationResult.isValid()) {
            switch (type) {
                case "автомобиль":
                    transport.setTax(ServiceTax.AUTOMOBILE.getTax());
                    break;
                case "мотоцикл":
                    transport.setTax(ServiceTax.MOTOBIKE.getTax());
                    break;
                case "микроавтобус":
                    transport.setTax(ServiceTax.MICROAUTOBUS.getTax());
                    break;
                default:
                    transport.setTax(0);
            }
        }
        return transport;
    }
}
