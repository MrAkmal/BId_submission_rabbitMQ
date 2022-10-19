package com.example.bid_submission_rabbitmq.rabbitmq;


import com.example.bid_submission_rabbitmq.dto.RabbitDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {


    private final AmqpTemplate rabbitTemplate;

    private final RabbitMQConfig rabbitMQConfig;

    @Autowired
    public RabbitMQProducer(AmqpTemplate rabbitTemplate, RabbitMQConfig rabbitMQConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQConfig = rabbitMQConfig;
    }

    public void send(RabbitDTO dto) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.exchange, rabbitMQConfig.routingKey, dto);
    }

}
