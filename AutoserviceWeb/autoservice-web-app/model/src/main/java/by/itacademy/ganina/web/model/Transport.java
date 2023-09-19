package by.itacademy.ganina.web.model;

import java.util.Objects;

public class Transport {

    private final String type;
    private final String model;
    private final Integer tax;

    public Transport(final String type, final String model, final Integer tax) {
        this.type = type;
        this.model = model;
        this.tax = tax;
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

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Transport transport = (Transport) object;
        return Objects.equals(type, transport.type)
                && Objects.equals(model, transport.model)
                && Objects.equals(tax, transport.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, model, tax);
    }

    @Override
    public String toString() {
        if (tax == null) {
            return String.join("|", type, model);
        } else {
            return String.join("|", type, model, tax.toString());
        }
    }
}