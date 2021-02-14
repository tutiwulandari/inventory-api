package com.enigma.api.inventory.Repositories;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ALL")
@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;


    @Test
    void shouldSave() {
        Item item = new Item();
        item.setName("xyz");
        item.setPrice(1000.0);

        repository.save(item);

        Item savedItem = repository.findById(item.getId()).get();
        assertEquals(item, savedItem);
    }

    @Test
    void testFindById() {
        Item item = new Item();
        item.setName("xyz");
        item.setPrice(1000.0);
        repository.save(item);

        Item test = repository.findById(item.getId()).get();
    }

    @Test
    void testFindAll() {
        Item item = new Item();
        item.setName("xyz");
        item.setPrice(1000.0);
        repository.save(item);

        List<Item> items= repository.findAll();
        assertThat(items).hasSize(1).contains(item);
    }

    @Test
    void shouldUpdateItem() {
        Item item = new Item();
        item.setName("xyz");
        item.setPrice(1000.0);
        repository.save(item);


        Item update = new Item();
        item.setName("xyz");
        item.setPrice(1000.0);
        repository.save(update);

        Item test =  repository.findById(item.getId()).get();

        assertThat(test.getId()).isEqualTo(item.getId());
        assertThat(test.getName()).isEqualTo(item.getName());
        assertThat(test.getPrice()).isEqualTo(item.getPrice());

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
