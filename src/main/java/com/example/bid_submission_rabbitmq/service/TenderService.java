package com.example.bid_submission_rabbitmq.service;


import com.example.bid_submission_rabbitmq.dto.ResponseData;
import com.example.bid_submission_rabbitmq.dto.tender.TenderCreateDTO;
import com.example.bid_submission_rabbitmq.dto.tender.TenderDTO;
import com.example.bid_submission_rabbitmq.dto.tender.TenderUpdateDTO;
import com.example.bid_submission_rabbitmq.entity.Tender;
import com.example.bid_submission_rabbitmq.mapper.TenderMapper;
import com.example.bid_submission_rabbitmq.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenderService {


    private final TenderRepository repository;
    private final TenderMapper mapper;

    private final UserService userService;

    @Autowired
    public TenderService(TenderRepository repository, TenderMapper mapper, UserService userService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public ResponseEntity<ResponseData<Void>> create(TenderCreateDTO dto, Long userId) {

        if (!userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        Tender tender = mapper.fromCreateDTO(dto);

        repository.save(tender);

        return new ResponseEntity<>(new ResponseData<>(null, "Successfully created"), HttpStatus.CREATED);

    }

    public ResponseEntity<ResponseData<List<TenderDTO>>> getAll() {
        return new ResponseEntity<>(new ResponseData<>(repository.getAll(), "Success"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<TenderDTO>> get(Long tenderId) {

        Optional<TenderDTO> optionalTenderDTO = repository.get(tenderId);

        if (optionalTenderDTO.isEmpty()) throw new RuntimeException("Tender not found");

        return new ResponseEntity<>(new ResponseData<>(optionalTenderDTO.get(), "Success"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<TenderDTO>> update(TenderUpdateDTO dto, Long userId) {

        if (!userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        if (!repository.existsById(dto.getId())) throw new RuntimeException("Tender not found");

        Tender tender = mapper.fromUpdateDTO(dto);

        repository.save(tender);

        return new ResponseEntity<>(new ResponseData<>(mapper.toDTO(tender), "Successfully Updated"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<Void>> delete(Long tenderId, Long userId) {

        if (!userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        if (!repository.existsById(tenderId)) throw new RuntimeException("Tender not found");

        repository.deleteById(tenderId);

        return new ResponseEntity<>(new ResponseData<>(null, "Successfully Deleted"), HttpStatus.OK);
    }


    public Tender findByTenderId(Long tenderId) {
        Optional<Tender> optionalTender = repository.findById(tenderId);

        if (optionalTender.isEmpty()) throw new RuntimeException("Tender not found");

        return optionalTender.get();
    }


}
