package by.itacademy.ganina.validation.processor;

public interface ValidationProcessor {

    ValidationResult validate(Object object) throws ValidationProcessorException;
}
