package com.example.bid_submission_rabbitmq.dto.tender;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenderUpdateDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String description;

}
