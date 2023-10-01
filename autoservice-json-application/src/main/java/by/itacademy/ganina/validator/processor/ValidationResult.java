package by.itacademy.ganina.validator.processor;

public class ValidationResult {

    private final String message;

    private final boolean isValid;

    public ValidationResult(final String message, final boolean isValid) {
        this.message = message;
        this.isValid = isValid;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return isValid;
    }
}
