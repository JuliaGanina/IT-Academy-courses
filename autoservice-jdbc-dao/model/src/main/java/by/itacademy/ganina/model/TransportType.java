package by.itacademy.ganina.model;

public enum TransportType implements IdField {
    CAR(1, "CAR"),
    TRUCK(2, "TRUCK"),
    MICROAUTOBUS(3, "MICROAUTOBUS");

    private final Integer id;
    private final String typeName;

    TransportType(final Integer id, final String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return String.join(" | ",
                id.toString(),
                typeName
        );
    }
}
