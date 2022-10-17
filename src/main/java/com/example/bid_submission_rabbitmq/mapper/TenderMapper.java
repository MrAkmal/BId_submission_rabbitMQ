package com.example.bid_submission_rabbitmq.mapper;

import com.example.bid_submission_rabbitmq.dto.tender.TenderCreateDTO;
import com.example.bid_submission_rabbitmq.dto.tender.TenderDTO;
import com.example.bid_submission_rabbitmq.dto.tender.TenderUpdateDTO;
import com.example.bid_submission_rabbitmq.entity.Tender;
import org.springframework.stereotype.Component;

@Component
public class TenderMapper {


    public Tender fromCreateDTO(TenderCreateDTO dto) {

        return Tender.builder()
                .description(dto.getDescription())
                .build();
    }

    public Tender fromUpdateDTO(TenderUpdateDTO dto) {
        return Tender.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .build();
    }

    public TenderDTO toDTO(Tender tender) {
        return TenderDTO.builder()
                .id(tender.getId())
                .description(tender.getDescription())
                .build();
    }
}
