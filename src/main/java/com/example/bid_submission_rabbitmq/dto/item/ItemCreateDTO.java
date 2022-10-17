package com.example.bid_submission_rabbitmq.dto.item;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Long quantity;

    @NotNull
    private Long tenderFormId;

}
