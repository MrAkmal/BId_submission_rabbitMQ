package com.example.bid_submission_rabbitmq.dto.item;

import com.example.bid_submission_rabbitmq.entity.Item;
import com.example.bid_submission_rabbitmq.entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRateDTO {


    @NotNull
    private Item item;

    @NotNull
    private User bidder;

    @NotNull
    private BigDecimal rate;

}
