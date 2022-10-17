package com.example.bid_submission_rabbitmq.service;

import com.example.bid_submission_rabbitmq.dto.BidSubmissionDTO;
import com.example.bid_submission_rabbitmq.dto.ResponseData;
import com.example.bid_submission_rabbitmq.dto.item.*;
import com.example.bid_submission_rabbitmq.entity.Item;
import com.example.bid_submission_rabbitmq.entity.Tender;
import com.example.bid_submission_rabbitmq.entity.TenderForm;
import com.example.bid_submission_rabbitmq.entity.User;
import com.example.bid_submission_rabbitmq.mapper.ItemMapper;
import com.example.bid_submission_rabbitmq.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {


    private final ItemRepository repository;
    private final ItemMapper mapper;
    private final UserService userService;
    private final TenderFormService tenderFormService;

    private final TenderService tenderService;
    private final BidSubmissionService bidSubmissionService;

    private final ItemRateService itemRateService;

    @Autowired
    public ItemService(ItemRepository repository, ItemMapper mapper, UserService userService, TenderFormService tenderFormService, TenderService tenderService, BidSubmissionService bidSubmissionService, ItemRateService itemRateService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
        this.tenderFormService = tenderFormService;
        this.tenderService = tenderService;
        this.bidSubmissionService = bidSubmissionService;
        this.itemRateService = itemRateService;
    }

    public ResponseEntity<ResponseData<Void>> create(ItemCreateDTO dto, Long userId) {

        if (!userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        TenderForm tenderForm = tenderFormService.findByTenderFormId(dto.getTenderFormId());

        Item item = mapper.fromCreateDTO(dto, tenderForm);

        Optional<Item> optionalItem = repository.findByNameAndDescriptionAndTenderForm(item.getName(), item.getDescription(), item.getTenderForm());

        if (optionalItem.isPresent()) throw new RuntimeException("Item already exists");

        repository.save(item);

        return new ResponseEntity<>(new ResponseData<>(null, "Successfully created"), HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseData<List<ItemDTO>>> getAll() {
        return new ResponseEntity<>(new ResponseData<>(repository.getAll(), "Success"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<ItemDTO>> get(Long itemId) {

        Optional<ItemDTO> optionalItemDTO = repository.get(itemId);

        if (optionalItemDTO.isEmpty()) throw new RuntimeException("Item not found");

        return new ResponseEntity<>(new ResponseData<>(optionalItemDTO.get(), "Success"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<ItemDTO>> update(ItemUpdateDTO dto, Long userId) {

        if (!userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        if (!repository.existsById(dto.getId())) throw new RuntimeException("Item not found");

        TenderForm tenderForm = tenderFormService.findByTenderFormId(dto.getTenderFormId());

        Item item = mapper.fromUpdateDTO(dto, tenderForm);

        Optional<Item> optionalItem = repository.findByNameAndDescriptionAndTenderForm(item.getName(), item.getDescription(), item.getTenderForm());

        if (optionalItem.isPresent()) throw new RuntimeException("Item already exists");

        repository.save(item);

        return new ResponseEntity<>(new ResponseData<>(mapper.toDTO(item, null), "Successfully updated"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<Void>> delete(Long itemId, Long userId) {

        if (!userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        if (!repository.existsById(itemId)) throw new RuntimeException("Item not found");

        repository.deleteById(itemId);

        return new ResponseEntity<>(new ResponseData<>(null, "Successfully deleted"), HttpStatus.NO_CONTENT);

    }


    public ResponseEntity<ResponseData<Void>> saveRateByBidder(ItemRateCreateDTO dto, Long userId) {

        if (userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        Optional<Item> optionalItem = repository.findById(dto.getItemId());

        if (optionalItem.isEmpty()) throw new RuntimeException("Item not found");

        Item item = optionalItem.get();

        User user = userService.findById(userId);

        itemRateService.save(new ItemRateDTO(item, user, dto.getRate()));

        return new ResponseEntity<>(new ResponseData<>(null, "Successfully updated"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<Void>> sumUpTotalRate(Long bidderId, Long tenderId) {

        if (userService.isAgencyUser(bidderId)) throw new RuntimeException("Permission denied");

        Tender tender = tenderService.findByTenderId(tenderId);

        BigDecimal totalRate = itemRateService.sumUpTotal(bidderId, tenderId);

        bidSubmissionService.updateTotalRate(new BidSubmissionDTO(tender, userService.findById(bidderId), totalRate, LocalDateTime.now()));

        return new ResponseEntity<>(new ResponseData<>(null, "Successfully calculated"), HttpStatus.CREATED);
    }


    public ResponseEntity<ResponseData<List<ItemDTO>>> getAllByTenderId(Long userId, Long tenderId) {

        if (userService.isAgencyUser(userId)) throw new RuntimeException("Permission denied");

        tenderService.findByTenderId(tenderId);

        List<ItemDTO> items = repository.findAllByTenderId(userId, tenderId);

        return new ResponseEntity<>(new ResponseData<>(items, "Success"), HttpStatus.OK);

    }

}
