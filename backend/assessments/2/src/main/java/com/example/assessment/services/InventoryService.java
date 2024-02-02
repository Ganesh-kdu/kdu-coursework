package com.example.assessment.services;

import com.example.assessment.dto.InventoryItemDto;
import com.example.assessment.entity.Item;
import com.example.assessment.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InventoryService {
    ItemsRepository itemsRepository;
    public InventoryService(ItemsRepository itemsRepository){
        this.itemsRepository = itemsRepository;
    }
    public UUID add(InventoryItemDto inventoryItemDto){
        UUID id = UUID.randomUUID();
        itemsRepository.save(new Item(id, inventoryItemDto.getName(), inventoryItemDto.getDescription(),inventoryItemDto.getPrice(),inventoryItemDto.getQuantity()));
        return id;
    }

    public String update(String uuid, InventoryItemDto inventoryItemDto){
        itemsRepository.save(new Item(java.util.UUID.fromString(uuid), inventoryItemDto.getName(), inventoryItemDto.getDescription(),inventoryItemDto.getPrice(),inventoryItemDto.getQuantity()));
        return "Successfully updated";
    }
}
