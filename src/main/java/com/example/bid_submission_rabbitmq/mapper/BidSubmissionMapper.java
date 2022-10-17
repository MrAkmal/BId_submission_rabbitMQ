package com.example.bid_submission_rabbitmq.mapper;

import com.example.bid_submission_rabbitmq.dto.BidSubmissionDTO;
import com.example.bid_submission_rabbitmq.entity.BidSubmission;
import org.springframework.stereotype.Component;

@Component
public class BidSubmissionMapper {



    public BidSubmission fromDTO(BidSubmissionDTO dto){
        return BidSubmission.builder()
                .user(dto.getUser())
                .tender(dto.getTender())
                .totalRate(dto.getTotalRate())
                .submissionDateTime(dto.getSubmissionDateTime())
                .build();
    }


}
