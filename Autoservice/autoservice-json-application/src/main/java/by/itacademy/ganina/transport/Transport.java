package by.itacademy.ganina.transport;

import by.itacademy.ganina.validation.annotations.FieldsAnalyzer;

public class Transport {

    private static final String MODEL_VALIDATOR = "^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$";
    private static final String TYPE_VALIDATOR = "автомобиль|мотоцикл|микроавтобус";

    @FieldsAnalyzer(pattern = TYPE_VALIDATOR, message = "Transport type is not valid")
    private final String type;

    @FieldsAnalyzer(pattern = MODEL_VALIDATOR, message = "Transport model is not valid")
    private final String model;

    private Integer tax;

    private boolean isValid;

    public Transport(String type, String model, Integer tax, boolean isValid) {
        this.type = type;
        this.model = model;
        this.tax = tax;
        this.isValid = isValid;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public Integer getTax() {
        return tax;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        if (tax == 0) {
            return String.join("|", type, model);
        } else {
            return String.join("|", type, model, tax.toString());
        }
    }
}
