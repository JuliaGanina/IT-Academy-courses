package by.itacademy.ganina.transport;

public class Transport {

    private final String type;
    private final String model;
    private final Integer tax;
    private final boolean isValid;

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

    @Override
    public String toString() {
        if (tax == 0) {
            return String.join("|", type, model);
        } else {
            return String.join("|", type, model, tax.toString());
        }
    }
}
