package com.enigma.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UnitGetterSetterTest {

    private UnitModel model;
    private UnitResponse response;
    private UnitSearch search;

    @Test
    void UnitModelGetSet() {
        UnitModel expected = new UnitModel();
        expected.setId(1);
        expected.setCode("xyz");
        expected.setDescription("XYZ");

        UnitModel actual = new UnitModel();
        actual.setId(1);
        actual.setCode("xyz");
        actual.setDescription("XYZ");

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCode(),actual.getCode());
        assertEquals(expected.getDescription(),actual.getDescription());
    }
    @Test
    void UnitResponseGetSet() {
        UnitResponse expected = new UnitResponse();
        expected.setId(1);
        expected.setName("name");

        UnitResponse actual = new UnitResponse();
        actual.setId(1);
        actual.setName("name");

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(),actual.getName());

    }

    @Test
    void UnitSearchGetSet() {
        UnitSearch expected = new UnitSearch();
        expected.setCode("xyz");
        expected.setDescription("XYZ");

        UnitSearch actual = new UnitSearch();
        actual.setCode("xyz");
        actual.setDescription("XYZ");

        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getDescription(),actual.getDescription());


    }
}
