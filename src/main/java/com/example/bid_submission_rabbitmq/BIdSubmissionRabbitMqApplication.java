package com.example.bid_submission_rabbitmq;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class BIdSubmissionRabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(BIdSubmissionRabbitMqApplication.class, args);
    }

}
