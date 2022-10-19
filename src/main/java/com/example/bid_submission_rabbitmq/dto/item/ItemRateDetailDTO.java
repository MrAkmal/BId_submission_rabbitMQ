package com.example.bid_submission_rabbitmq.dto.item;


import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ItemRateDetailDTO {

    private String bidderUserName;
    private BigDecimal rate;

}
