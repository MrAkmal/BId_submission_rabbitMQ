package com.example.bid_submission_rabbitmq.dto.item;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRateCreateDTO {


    @NotNull
    private Long itemId;

    @NotNull
    private BigDecimal rate;

}