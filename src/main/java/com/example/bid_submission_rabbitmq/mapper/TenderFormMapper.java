package com.example.bid_submission_rabbitmq.mapper;

import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormCreateDTO;
import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormDTO;
import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormUpdateDTO;
import com.example.bid_submission_rabbitmq.entity.Tender;
import com.example.bid_submission_rabbitmq.entity.TenderForm;
import org.springframework.stereotype.Component;

@Component
public class TenderFormMapper {


    public TenderForm fromCreateDTO(TenderFormCreateDTO dto, Tender tender) {
        return TenderForm.builder()
                .name(dto.getName())
                .tender(tender)
                .build();
    }


    public TenderForm fromUpdateDTO(TenderFormUpdateDTO dto, Tender tender) {
        return TenderForm.builder()
                .id(dto.getId())
                .name(dto.getName())
                .tender(tender)
                .build();
    }

    public TenderFormDTO toDTO(TenderForm tenderForm) {
        return TenderFormDTO.builder()
                .id(tenderForm.getId())
                .name(tenderForm.getName())
                .build();
    }
}
