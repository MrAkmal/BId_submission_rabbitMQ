package com.example.bid_submission_rabbitmq.service;


import com.example.bid_submission_rabbitmq.dto.ResponseData;
import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormCreateDTO;
import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormDTO;
import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormUpdateDTO;
import com.example.bid_submission_rabbitmq.entity.Tender;
import com.example.bid_submission_rabbitmq.entity.TenderForm;
import com.example.bid_submission_rabbitmq.mapper.TenderFormMapper;
import com.example.bid_submission_rabbitmq.repository.TenderFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenderFormService {


    private final TenderFormRepository repository;

    private final TenderFormMapper mapper;

    private final UserService userService;

    private final TenderService tenderService;


    @Autowired
    public TenderFormService(TenderFormRepository repository, TenderFormMapper mapper, UserService userService, TenderService tenderService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
        this.tenderService = tenderService;
    }

    public ResponseEntity<ResponseData<Void>> create(TenderFormCreateDTO dto, Long userId) {

        if (!userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        Tender tender = tenderService.findByTenderId(dto.getTenderId());

        Optional<TenderForm> optionalTenderForm = repository.findByNameAndTender(dto.getName(), tender);

        if (optionalTenderForm.isPresent()) throw new RuntimeException("Tender form already exists");

        repository.save(mapper.fromCreateDTO(dto, tender));

        return new ResponseEntity<>(new ResponseData<>(null, "Successfully created"), HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseData<List<TenderFormDTO>>> getAll() {
        return new ResponseEntity<>(new ResponseData<>(repository.getAll(), "Success"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<TenderFormDTO>> get(Long tenderFormId) {

        Optional<TenderFormDTO> optionalTenderFormDTO = repository.get(tenderFormId);

        if (optionalTenderFormDTO.isEmpty()) throw new RuntimeException("Tender form not found");

        return new ResponseEntity<>(new ResponseData<>(optionalTenderFormDTO.get(), "Success"), HttpStatus.OK);

    }

    public ResponseEntity<ResponseData<TenderFormDTO>> update(TenderFormUpdateDTO dto, Long userId) {

        if (!userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        Tender tender = tenderService.findByTenderId(dto.getTenderId());

        Optional<TenderForm> optionalTenderForm = repository.findById(dto.getId());

        if (optionalTenderForm.isEmpty()) throw new RuntimeException("Tender form not found");

        TenderForm tenderForm = mapper.fromUpdateDTO(dto, tender);

        Optional<TenderForm> tenderFormOptional = repository.findByNameAndTender(tenderForm.getName(), tenderForm.getTender());

        if (tenderFormOptional.isPresent()) throw new RuntimeException("Tender form already exists");

        repository.save(tenderForm);

        return new ResponseEntity<>(new ResponseData<>(mapper.toDTO(tenderForm), "Successfully updated"), HttpStatus.OK);
    }


    public ResponseEntity<ResponseData<Void>> delete(Long tenderFormId, Long userId) {

        if (!userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        if (!repository.existsById(tenderFormId)) throw new RuntimeException("Tender form not found");

        repository.deleteById(tenderFormId);

        return new ResponseEntity<>(new ResponseData<>(null, "Successfully deleted"), HttpStatus.OK);

    }


    public TenderForm findByTenderFormId(Long tenderFormId) {

        Optional<TenderForm> optionalTenderForm = repository.findById(tenderFormId);

        if (optionalTenderForm.isEmpty()) throw new RuntimeException("Tender form not found");

        return optionalTenderForm.get();

    }


    public ResponseEntity<ResponseData<List<TenderFormDTO>>> getAllByTenderId(Long tenderId) {
        return new ResponseEntity<>(new ResponseData<>(repository.getAllByTenderId(tenderId), "Success"), HttpStatus.OK);
    }
}
