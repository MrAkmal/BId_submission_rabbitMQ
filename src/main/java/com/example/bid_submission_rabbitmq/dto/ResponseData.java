package com.example.bid_submission_rabbitmq.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData<D> {


    private D data;

    private String message;

}
