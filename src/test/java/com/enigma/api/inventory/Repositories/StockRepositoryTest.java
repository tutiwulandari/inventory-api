package com.enigma.api.inventory.Repositories;

import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.repositories.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ALL")
@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository repository;


    @Test
    void shouldSave() {
        Stock stock = new Stock();
        stock.setQuantity(10);

        repository.save(stock);

        Stock savedStock = repository.findById(stock.getId()).get();
        assertEquals(stock, savedStock);
    }

    @Test
    void testFindById() {
        Stock stock = new Stock();
        stock.setQuantity(100);
        repository.save(stock);

       Stock test = repository.findById(stock.getId()).get();
    }

    @Test
    void testFindAll() {
        Stock stock = new Stock();
        stock.setQuantity(100);
        repository.save(stock);

        List<Stock> stocks = repository.findAll();
        assertThat(stocks).hasSize(1).contains(stock);
    }
    @Test
    void shouldUpdateItem() {
        Stock stock = new Stock();
        stock.setQuantity(100);
        repository.save(stock);


        Stock update = new Stock();
        stock.setQuantity(100);
        repository.save(update);

        Stock test =  repository.findById(stock.getId()).get();

        assertThat(test.getId()).isEqualTo(stock.getId());
        assertThat(test.getQuantity()).isEqualTo(stock.getQuantity());

    }

    @Test
    void shouldDeleteEntity() {
        Stock stock = new Stock();
        stock.setQuantity(100);
        repository.save(stock);

        repository.deleteAll();
        assertThat(repository.findAll().isEmpty());
    }

}
