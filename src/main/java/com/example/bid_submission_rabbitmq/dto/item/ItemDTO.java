package com.example.bid_submission_rabbitmq.dto.item;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {


    private Long id;

    private String name;

    private String description;

    private Long quantity;

    private BigDecimal rate;

    public ItemDTO(Long id, String name, String description, Long quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }
}
