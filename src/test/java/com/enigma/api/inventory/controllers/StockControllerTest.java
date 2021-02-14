package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.models.*;
import com.enigma.api.inventory.services.FileService;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.StockService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SuppressWarnings("ALL")
@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private StockService service;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private Item item;

    @MockBean
    private FileService fileService;

    @MockBean
    private StockResponse stockResponse;

    @Test
    void addShouldSuccess() throws Exception {

        Stock stock = new Stock();
        stock.setId(1);
        stock.setQuantity(10);
        stock.setItem(item);

        Item item = new Item();
        item.setId(1);
        item.setName("ABC");
        item.setPrice(10.0);
        item.setUnit(item.getUnit());

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(1);
        itemResponse.setPrice(10.0);
        itemResponse.setName("ABC");
        itemResponse.setUnit(itemResponse.getUnit());

        StockResponse response = new StockResponse();
        response.setId(stock.getId());
        response.setQuantity(stock.getQuantity());
        response.setItem(itemResponse);

        when(modelMapper.map(any(StockRequest.class), any(Class.class))).thenReturn(stock);
        when(itemService.findById(anyInt())).thenReturn(item);
        when(service.save(any(Stock.class))).thenReturn(stock);
        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(response);

        RequestBuilder request = post("/stocks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"quantity\": \"10\", \"itemId\": \"1\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(stock.getQuantity())));
    }

    @Test
    public void getShouldReturnEntity() throws Exception {

        Stock stock = new Stock();
        stock.setId(1);
        stock.setQuantity(10);
        stock.setItem(item);

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(1);
        itemResponse.setPrice(10.0);
        itemResponse.setName("ABC");
        itemResponse.setUnit(itemResponse.getUnit());

        StockResponse response = new StockResponse();
        response.setId(stock.getId());
        response.setQuantity(stock.getQuantity());
        response.setItem(itemResponse);

        when(service.findById(1)).thenReturn(stock);

        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(response);

        RequestBuilder request = get("/stocks/1")
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(stock.getQuantity())));
    }

    @Test
    void shouldDeleteEntity() throws Exception {

        Stock stock = new Stock(1, 10);
        Mockito.when(service.removeById(1)).thenReturn(stock);

        Integer modelId = 1;
        StockResponse model = new StockResponse();
        model.setId(modelId);
        model.setQuantity(10);

        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(model);

        mvc.perform(delete("/stocks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(stock.getQuantity())));

    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page page = Page.empty();

        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/stocks");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    void editShouldSuccess() throws Exception {
        Item item = new Item();
        item.setId(1);
        item.setName("ABC");
        item.setPrice(10.0);
        item.setUnit(item.getUnit());

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(1);
        itemResponse.setPrice(10.0);
        itemResponse.setName("ABC");
        itemResponse.setUnit(itemResponse.getUnit());

        Stock stock = new Stock();
        stock.setId(1);
        stock.setQuantity(10);
        stock.setItem(item);

        StockResponse response = new StockResponse();
        response.setId(stock.getId());
        response.setQuantity(stock.getQuantity());
        response.setItem(itemResponse);

        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(stock);
        when(service.save(any(Stock.class))).thenReturn(stock);
        when(service.findById(anyInt())).thenReturn(stock);
        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(response);


        RequestBuilder request = put("/stocks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"quantity\": \"10\", \"itemId\": \"1\"}");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(stock.getQuantity())));

    }
}
