package by.itacademy.ganina.web.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportTest {

    @Test
    void testGetType_happyPath() {
        //given
        final String expectedType = "type";
        final Transport transport = new Transport(expectedType, null, null);

        //when
        final String actualType = transport.getType();

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
    void testGetTax_happyPath() {
        //given
        final Integer expectedTax = 10;
        final Transport transport = new Transport(null, null, expectedTax);

        //when
        final Integer actualTax = transport.getTax();

        //then
        assertEquals(expectedTax, actualTax);
    }

/*
    @Test
    void testToString_happyPath() {

    }*/
}