package by.itacademy.ganina.web.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportTest {

    @Test
    void testGetType_happyPath() {
        //given
        final TransportType expectedType = TransportType.AUTOMOBILE;
        final Transport transport = new Transport(expectedType, null, null);

        //when
        final String actualType = transport.getTransportType().name();

        //then
        assertEquals(expectedType, actualType);
    }

    @Test
    void testGetModel_happyPath() {
        //given
        final String expectedModel = "model";
        final Transport transport = new Transport(null, expectedModel, null);

        //when
        final String actualModel = transport.getModel();

        //then
        assertEquals(expectedModel, actualModel);
    }

    @Test
    void testGetPrice_happyPath() {
        //given
        final Integer expectedPrice = TransportType.MOTOBIKE.getPrice();
        final Transport transport = new Transport(null, null, expectedPrice);

        //when
        final Integer actualprice = transport.getPrice();

        //then
        assertEquals(expectedPrice, actualprice);
    }
}