package com.example.bid_submission_rabbitmq.controller;

import com.example.bid_submission_rabbitmq.dto.ResponseData;
import com.example.bid_submission_rabbitmq.dto.tender.TenderCreateDTO;
import com.example.bid_submission_rabbitmq.dto.tender.TenderDTO;
import com.example.bid_submission_rabbitmq.dto.tender.TenderUpdateDTO;
import com.example.bid_submission_rabbitmq.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tender")
@CrossOrigin("*")
public class TenderController {


    private final TenderService service;

    @Autowired
    public TenderController(TenderService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ResponseData<Void>> create(@RequestBody TenderCreateDTO dto, @RequestParam("userId") Long userId) {
        return service.create(dto, userId);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<TenderDTO>>> getAll() {
        return service.getAll();
    }


    @GetMapping("/{tenderId}")
    public ResponseEntity<ResponseData<TenderDTO>> get(@PathVariable Long tenderId) {
        return service.get(tenderId);
    }


    @PutMapping
    public ResponseEntity<ResponseData<TenderDTO>> update(@RequestParam TenderUpdateDTO dto, @RequestParam("userId") Long userId) {
        return service.update(dto, userId);
    }

    @DeleteMapping("/{tenderId}")
    public ResponseEntity<ResponseData<Void>> delete(@PathVariable Long tenderId, @RequestParam("userId") Long userId) {
        return service.delete(tenderId, userId);
    }


}
