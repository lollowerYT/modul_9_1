package com.example.shoppinglist.controller;

import com.example.shoppinglist.model.ShoppingItem;
import com.example.shoppinglist.service.ShoppingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Базовый путь
public class ShoppingItemController {

    @Autowired
    private ShoppingItemService itemService;

    // Получить все элементы списка
    @GetMapping("/items")
    public List<ShoppingItem> getAllItems() {
        return itemService.findAll();
    }

    // Добавить новый элемент
    @PostMapping("/items")
    public ShoppingItem addItem(@RequestBody ShoppingItem item) {
        // @RequestBody извлекает JSON из тела запроса и преобразует в объект
        return itemService.save(item);
    }

    // Удалить элемент по ID
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Отметить элемент как купленный
    @PatchMapping("/items/{id}")
    public ShoppingItem markAsPurchased(@PathVariable Long id, @RequestBody ShoppingItem updatedItem) {
        return itemService.updatePurchasedStatus(id, updatedItem.isPurchased());
    }
}