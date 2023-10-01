package by.itacademy.ganina.validation.processor.impl;

import by.itacademy.ganina.validation.annotations.FieldsAnalyzer;
import by.itacademy.ganina.validation.processor.ValidationProcessor;
import by.itacademy.ganina.validation.processor.ValidationProcessorException;
import by.itacademy.ganina.validation.processor.ValidationResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FieldsValidationProcessor implements ValidationProcessor {

    @Override
    public ValidationResult validate(Object object) throws ValidationProcessorException {
        String message = null;
        for (Field field : object.getClass().getDeclaredFields()) {
            message = processField(field, object);
            if (message != null) {
                break;
            }
        }

        final boolean isValidField = (message == null);
        return new ValidationResult(message, isValidField);
    }

    private String processField(final Field field, final Object object) throws ValidationProcessorException {
        String message = null;

        for (final Annotation annotation : field.getDeclaredAnnotations()) {
            if (!(annotation instanceof FieldsAnalyzer)) {
                continue;
            }
            if (!field.canAccess(object) && !field.trySetAccessible()) {
                continue;
            }

            final FieldsAnalyzer fieldsAnalyzer = (FieldsAnalyzer) annotation;

            try {
                final Object fieldValue = field.get(object);
                if (!(fieldValue instanceof String)) {
                    continue;
                }
                final String value = (String) fieldValue;

                final Predicate<String> predicate = Pattern.compile(fieldsAnalyzer.pattern()).asPredicate();
                if (predicate.test(value)) {
                    continue;
                }

                message = fieldsAnalyzer.isValidField()
                        ? fieldsAnalyzer.message().formatted(value)
                        : fieldsAnalyzer.message();
            } catch (final IllegalAccessException ex) {
                throw new ValidationProcessorException("Failed to process Parameter annotation", ex);
            }
        }
        return message;
    }
}
