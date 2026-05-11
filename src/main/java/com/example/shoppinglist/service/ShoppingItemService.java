package com.example.shoppinglist.service;

import com.example.shoppinglist.model.ShoppingItem;
import com.example.shoppinglist.repository.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingItemService {

    @Autowired
    private ShoppingItemRepository itemRepository;

    public List<ShoppingItem> findAll() {
        return itemRepository.findAll();
    }

    public ShoppingItem save(ShoppingItem item) {
        return itemRepository.save(item);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    public ShoppingItem updatePurchasedStatus(Long id, boolean purchased) {
        Optional<ShoppingItem> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            ShoppingItem item = optionalItem.get();
            item.setPurchased(purchased);
            return itemRepository.save(item);
        }
        return null;
    }
}