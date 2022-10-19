package com.example.bid_submission_rabbitmq.controller;

import com.example.bid_submission_rabbitmq.dto.ResponseData;
import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormCreateDTO;
import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormDTO;
import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormUpdateDTO;
import com.example.bid_submission_rabbitmq.service.TenderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tender_form")
@CrossOrigin("*")
public class TenderFormController {

    private final TenderFormService service;

    @Autowired
    public TenderFormController(TenderFormService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ResponseData<Void>> create(@RequestBody TenderFormCreateDTO dto, @RequestParam("userId") Long userId) {
        return service.create(dto, userId);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<TenderFormDTO>>> getAll() {
        return service.getAll();
    }


    @GetMapping("/{tenderFormId}")
    public ResponseEntity<ResponseData<TenderFormDTO>> get(@PathVariable Long tenderFormId) {
        return service.get(tenderFormId);
    }


    @PutMapping
    public ResponseEntity<ResponseData<TenderFormDTO>> update(@RequestParam TenderFormUpdateDTO dto, @RequestParam("userId") Long userId) {
        return service.update(dto, userId);
    }

    @DeleteMapping("/{tenderFormId}")
    public ResponseEntity<ResponseData<Void>> delete(@PathVariable Long tenderFormId, @RequestParam("userId") Long userId) {
        return service.delete(tenderFormId, userId);
    }

    @GetMapping("/tender/{tenderId}")
    public ResponseEntity<ResponseData<List<TenderFormDTO>>> getAllByTenderId(@PathVariable Long tenderId) {
        return service.getAllByTenderId(tenderId);
    }


}
