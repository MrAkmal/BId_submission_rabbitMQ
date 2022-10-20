package com.example.bid_submission_rabbitmq;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition
public class BIdSubmissionRabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(BIdSubmissionRabbitMqApplication.class, args);
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.setAllowedOrigins(List.of(
                "http://localhost:4500/")
        );
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
