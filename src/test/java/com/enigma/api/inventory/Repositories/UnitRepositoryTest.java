package com.enigma.api.inventory.Repositories;

import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repositories.UnitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UnitRepositoryTest {

    @Autowired
    private UnitRepository repository;

    @Test
    void shouldSave() {
        Unit unit = new Unit();
        unit.setCode("xyz");
        unit.setDescription("XYZ");

        repository.save(unit);

        Unit savedUnit = repository.findById(unit.getId()).get();
        assertEquals(unit, savedUnit);
    }

    @Test
    void testFindById() {
        Optional<Unit> optUnit = repository.findById(1);
        assertTrue(optUnit.isPresent());
    }

    @Test
    void testFindAll() {
        List<Unit> units = repository.findAll();
        assertEquals(2, units.size());
    }
    @Test
    void shouldUpdateUnit() {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("xyz");
        unit.setDescription("XYZ");

        repository.save(unit);

        Unit update = new Unit();
        update.setId(1);
        update.setCode("xyz");
        update.setDescription("XYZ");
        repository.save(update);

       Unit result =  repository.findById(unit.getId()).get();

       assertThat(result.getId()).isEqualTo(unit.getId());
       assertThat(result.getCode()).isEqualTo(unit.getCode());
       assertThat(result.getDescription()).isEqualTo(unit.getDescription());

    }

    @Test
    void shouldDeleteEntity() {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("xyz");
        unit.setDescription("XYZ");

        repository.deleteAll();
        assertThat(repository.findAll().isEmpty());
    }
}
