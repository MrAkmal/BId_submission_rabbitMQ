package com.example.bid_submission_rabbitmq.dto.tender;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenderUpdateDTO {

    private Long id;

    private String description;

}
