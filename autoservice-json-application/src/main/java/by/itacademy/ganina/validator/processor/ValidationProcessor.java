package by.itacademy.ganina.validator.processor;

public interface ValidationProcessor {

    ValidationResult validate(Object object) throws ValidationProcessorException;
}
