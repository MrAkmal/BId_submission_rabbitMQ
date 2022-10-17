package com.example.bid_submission_rabbitmq.dto.tenderform;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenderFormCreateDTO {

    @NotBlank
    private String name;

    @NotNull
    private Long tenderId;


}
