package com.example.bid_submission_rabbitmq.mapper;

import com.example.bid_submission_rabbitmq.dto.item.ItemCreateDTO;
import com.example.bid_submission_rabbitmq.dto.item.ItemDTO;
import com.example.bid_submission_rabbitmq.dto.item.ItemUpdateDTO;
import com.example.bid_submission_rabbitmq.entity.Item;
import com.example.bid_submission_rabbitmq.entity.TenderForm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ItemMapper {
    public Item fromCreateDTO(ItemCreateDTO dto, TenderForm tenderForm) {

        return Item.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .quantity(dto.getQuantity())
                .tenderForm(tenderForm)
                .build();
    }


    public Item fromUpdateDTO(ItemUpdateDTO dto, TenderForm tenderForm) {
        return Item.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .quantity(dto.getQuantity())
                .tenderForm(tenderForm)
                .build();
    }

    public ItemDTO toDTO(Item item, BigDecimal rate) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .quantity(item.getQuantity())
                .rate(rate)
                .build();
    }
}
