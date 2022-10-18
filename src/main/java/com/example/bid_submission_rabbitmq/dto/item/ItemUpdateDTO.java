package com.example.bid_submission_rabbitmq.dto.item;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemUpdateDTO {

    @NotNull
    private Long id;

    private String name;

    private String description;

    private Long quantity;

    private Long tenderFormId;

}
