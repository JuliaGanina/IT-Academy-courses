package by.itacademy.ganina.web.model;

import java.util.Objects;

public class Transport {

    private final TransportType transportType;
    private final String model;
    private final Integer price;

    public Transport(final TransportType transportType, final String model, final Integer price) {
        this.transportType = transportType;
        this.model = model;
        this.price = price;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public String getModel() {
        return model;
    }

    public Integer getPrice() {
        return price;
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
        return transportType == transport.transportType
                && Objects.equals(model, transport.model)
                && Objects.equals(price, transport.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportType, model, price);
    }

    @Override
    public String toString() {
        if (transportType.getPrice() != null) {
            return String.join("|", transportType.name().toLowerCase(), model, transportType.getPrice().toString());
        } else {
            return String.join("|", transportType.name().toLowerCase(), model);
        }
    }
}