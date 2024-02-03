package com.example.assessment.services;

import com.example.assessment.dto.InventoryItemDto;
import com.example.assessment.entity.Item;
import com.example.assessment.repository.ItemsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public String delete(String uuid){
        itemsRepository.deleteById(UUID.fromString(uuid
        ));
        return "Successfully deleted";
    }

    public InventoryItemDto get(String id){
        Optional<Item> result = itemsRepository.findById(UUID.fromString(id));
        if(result.isEmpty()){
            throw new EntityNotFoundException("Details not found for given id");
        }
        Item item = result.get();
        return new InventoryItemDto(item.getName(),item.getDescription(),item.getPrice(),item.getQuantity());

    }
}
