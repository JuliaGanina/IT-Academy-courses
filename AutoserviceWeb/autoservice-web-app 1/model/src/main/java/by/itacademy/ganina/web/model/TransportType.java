package by.itacademy.ganina.web.model;

public enum TransportType {

    AUTOMOBILE(20),
    MOTOBIKE(10),
    MICROAUTOBUS(30);

    private final Integer price;

    TransportType(final Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
