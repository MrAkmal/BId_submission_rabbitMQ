package com.example.bid_submission_rabbitmq.dto;

import com.example.bid_submission_rabbitmq.entity.Tender;
import com.example.bid_submission_rabbitmq.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BidSubmissionDTO {


    private Long id;

    private Tender tender;

    private User user;

    private BigDecimal totalRate;

    private LocalDateTime submissionDateTime;


    public BidSubmissionDTO(Tender tender, User user, BigDecimal totalRate, LocalDateTime submissionDateTime) {
        this.tender = tender;
        this.user = user;
        this.totalRate = totalRate;
        this.submissionDateTime = submissionDateTime;
    }


}
