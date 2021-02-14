package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.*;
import com.enigma.api.inventory.models.StockElement;
import com.enigma.api.inventory.models.StockRequest;
import com.enigma.api.inventory.models.StockResponse;
import com.enigma.api.inventory.models.StockSearch;
import com.enigma.api.inventory.entities.StockSummary;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/stocks")
@RestController
public class StockController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private StockService stockService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<StockResponse> add(
            @RequestBody @Valid StockRequest model) {
        Stock entity = modelMapper.map(model, Stock.class);

        Item item = itemService.findById(model.getItemId());
        entity.setItem(item);

        entity = stockService.save(entity);

        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<StockResponse> edit(
            @PathVariable Integer id, @RequestBody @Valid StockRequest model) {
        Stock entity = stockService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        Item item = itemService.findById(model.getItemId());
        entity.setItem(item);
        modelMapper.map(model, entity);

        entity = stockService.save(entity);

        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("{id}")
    public ResponseMessage<StockResponse> removeById(@PathVariable Integer id) {
        Stock entity = stockService.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<StockResponse> findById(
            @PathVariable("id") Integer id) {

        Stock entity = stockService.findById(id);
        if (entity != null) {
            StockResponse data = modelMapper.map(entity, StockResponse.class);
            return ResponseMessage.success(data);
        }
        throw new EntityNotFoundException();
    }

    @GetMapping
    public ResponseMessage<PagedList<StockElement>> findAll(
            @Valid StockSearch model
    ) {
        Stock search = modelMapper.map(model, Stock.class);

        Page<Stock> entityPage = stockService.findAll(search,
                model.getPage(), model.getSize(), model.getSort());

        List<Stock> entities = entityPage.toList();

        List<StockElement> models = entities.stream()
                .map(e -> modelMapper.map(e, StockElement.class))
                .collect(Collectors.toList());

        PagedList<StockElement> data = new PagedList<>(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/summaries")
    public ResponseMessage<List<StockSummary>> findAllSummaries() {
        List<StockSummary> entityPage = stockService.findAllSummaries();
        return ResponseMessage.success(entityPage);

    }

}

