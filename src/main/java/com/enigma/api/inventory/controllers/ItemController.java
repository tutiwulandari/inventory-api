package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.ImageUploadRequest;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.ResponseMessage;
import com.enigma.api.inventory.models.ItemElement;
import com.enigma.api.inventory.models.ItemRequest;
import com.enigma.api.inventory.models.ItemResponse;
import com.enigma.api.inventory.models.ItemSearch;
import com.enigma.api.inventory.services.FileService;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@RequestMapping("/items")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Add Item", description = "Some Description...", tags = {"item"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server error",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseMessage.class))))
    })
    @PostMapping
    public ResponseMessage<ItemResponse> add(@RequestBody @Valid ItemRequest model) {
        Item entity = modelMapper.map(model, Item.class);

        Unit unit = unitService.findById(model.getUnitId());
        entity.setUnit(unit);

        entity = itemService.save(entity);

        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<ItemResponse> edit(
            @PathVariable Integer id, @RequestBody @Valid ItemRequest model) {
        Item entity = itemService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        Unit unit = unitService.findById(model.getUnitId());
        entity.setUnit(unit);

        modelMapper.map(model, entity);

        entity = itemService.save(entity);

        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("{id}")
    public ResponseMessage<ItemResponse> removeById(@PathVariable Integer id) {
        Item entity = itemService.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<ItemResponse> findById(@PathVariable("id") Integer id) {
        Item entity = itemService.findById(id);
        if (itemService.findById(id) != null) {
            ItemResponse data = modelMapper.map(entity, ItemResponse.class);
            return ResponseMessage.success(data);
        }
        throw new EntityNotFoundException();

    }

    @GetMapping
    public ResponseMessage<PagedList<ItemElement>> findAll(
            @Valid ItemSearch model
    ) {
        Item search = modelMapper.map(model, Item.class);

        Page<Item> entityPage = itemService.findAll(search,
                model.getPage(), model.getSize(), model.getSort());

        List<Item> entities = entityPage.toList();

        List<ItemElement> models = entities.stream()
                .map(e -> modelMapper.map(e, ItemElement.class))
                .collect(Collectors.toList());

        PagedList<ItemElement> data = new PagedList<>(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @PostMapping(value = "/{id}/upload", consumes = "multipart/form-data")
    public ResponseMessage upload(@PathVariable Integer id,
                                  @Valid ImageUploadRequest model) throws IOException {

        Item entity = itemService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        fileService.upload(entity, model.getFile().getInputStream());
        return ResponseMessage.success(true);
    }

    @GetMapping("/{id}/download")
    public void download(@PathVariable Integer id,
                         HttpServletResponse response) throws IOException {
        Item entity = itemService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + entity.getId() + "\"");
        fileService.download(entity, response.getOutputStream());
    }
}

