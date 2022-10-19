package com.example.bid_submission_rabbitmq.service;

import com.example.bid_submission_rabbitmq.dto.item.ItemRateDTO;
import com.example.bid_submission_rabbitmq.dto.item.ItemRateDetailDTO;
import com.example.bid_submission_rabbitmq.entity.ItemRate;
import com.example.bid_submission_rabbitmq.repository.ItemRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemRateService {

    private final ItemRateRepository repository;

    @Autowired
    public ItemRateService(ItemRateRepository repository) {
        this.repository = repository;
    }


    public void save(ItemRateDTO dto) {

        Optional<ItemRate> optionalItemRate = repository.findByItemAndBidder(dto.getItem(), dto.getBidder());

        if (optionalItemRate.isPresent()) throw new RuntimeException("ItemRate already exists");

        repository.save(new ItemRate(dto.getItem(), dto.getBidder(), dto.getRate()));

    }


    public BigDecimal sumUpTotal(Long bidderId, Long tenderId) {
        return repository.sumUpRate(bidderId, tenderId);
    }

    public List<ItemRateDetailDTO> findByItemId(Long itemId) {
        return repository.findByItemId(itemId);
    }
}
