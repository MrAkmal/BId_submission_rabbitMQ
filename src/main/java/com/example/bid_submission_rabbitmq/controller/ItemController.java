package com.example.bid_submission_rabbitmq.controller;

import com.example.bid_submission_rabbitmq.dto.ResponseData;
import com.example.bid_submission_rabbitmq.dto.item.*;
import com.example.bid_submission_rabbitmq.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/item")
public class ItemController {

    private final ItemService service;

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ResponseData<Void>> create(@RequestBody ItemCreateDTO dto, @RequestParam("userId") Long userId) {
        return service.create(dto, userId);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<ItemDTO>>> getAll() {
        return service.getAll();
    }


    @GetMapping("/{itemId}")
    public ResponseEntity<ResponseData<ItemDTO>> get(@PathVariable Long itemId) {
        return service.get(itemId);
    }


    @PutMapping
    public ResponseEntity<ResponseData<ItemDTO>> update(@RequestParam ItemUpdateDTO dto, @RequestParam("userId") Long userId) {
        return service.update(dto, userId);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ResponseData<Void>> delete(@PathVariable Long itemId, @RequestParam("userId") Long userId) {
        return service.delete(itemId, userId);
    }


    @PostMapping("/rate")
    public ResponseEntity<ResponseData<Void>> saveRateByBidder(@RequestBody ItemRateCreateDTO dto, @RequestParam("userId") Long userId) {
        return service.saveRateByBidder(dto, userId);
    }


    @GetMapping("/rate/{tenderId}")
    public ResponseEntity<ResponseData<List<ItemDTO>>> getAllByTenderId(@PathVariable Long tenderId, @RequestParam("userId") Long userId) {
        return service.getAllByTenderId(userId, tenderId);
    }


    @PostMapping("/rate/sum-up/{tenderId}")
    public ResponseEntity<ResponseData<Void>> sumUpRate(@PathVariable Long tenderId, @RequestParam("userId") Long userId) {
        return service.sumUpTotalRate(userId, tenderId);
    }

}
