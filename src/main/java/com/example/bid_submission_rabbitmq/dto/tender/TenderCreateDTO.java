package com.example.bid_submission_rabbitmq.dto.tender;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenderCreateDTO {

    @NotBlank
    private String description;
}
