package by.itacademy.ganina.transport;

public enum ServiceTax {

    AUTOMOBILE(20),
    MOTOBIKE(10),
    MICROAUTOBUS(30);
    private final Integer tax;

    ServiceTax(final Integer tax) {
        this.tax = tax;
    }

    public Integer getTax() {
        return tax;
    }

}

