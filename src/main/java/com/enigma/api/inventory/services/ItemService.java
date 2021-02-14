package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Item;
import org.springframework.stereotype.Service;


@Service
public interface ItemService extends CommonService<Item, Integer> {
}
