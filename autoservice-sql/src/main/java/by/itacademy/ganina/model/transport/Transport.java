package by.itacademy.ganina.model.transport;

import by.itacademy.ganina.model.client.Client;

public class Transport {

    private String model;
    private TransportType transportType;
    private Client client;

    public Transport(final String model,final TransportType transportType,final Client client) {
        this.model = model;
        this.transportType = transportType;
        this.client = client;
    }

    public String getModel() {
        return model;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return String.join(" | ",
                model,
                transportType.toString(),
                client == null ? null : client.toString());
    }
}
