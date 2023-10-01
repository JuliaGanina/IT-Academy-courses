package by.itacademy.ganina.model;

public class Transport extends BaseModel{

    private final ModelType modelType;
    private final TransportType transportType;
    private final Client client;

    public Transport(final Integer id, final ModelType modelType, final TransportType transportType, final Client client) {
        super(id);
        this.modelType = modelType;
        this.transportType = transportType;
        this.client = client;
    }

    public ModelType getModelType() {
        return modelType;
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
                getId().toString(),
                modelType.toString(),
                transportType.toString(),
                client == null ? null : client.toString()
        );
    }
}
