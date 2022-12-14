package com.example.bid_submission_rabbitmq.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RabbitDTO {

    private BigDecimal totalRate;

    private Long userId;

    private Long tenderId;

    private String submissionDateTime;

}
