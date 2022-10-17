package com.example.bid_submission_rabbitmq.dto.tender;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenderDTO {

    private Long id;

    private String description;

}
