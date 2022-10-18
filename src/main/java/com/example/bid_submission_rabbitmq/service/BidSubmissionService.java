package com.example.bid_submission_rabbitmq.service;

import com.example.bid_submission_rabbitmq.dto.BidSubmissionDTO;
import com.example.bid_submission_rabbitmq.dto.RabbitDTO;
import com.example.bid_submission_rabbitmq.entity.BidSubmission;
import com.example.bid_submission_rabbitmq.mapper.BidSubmissionMapper;
import com.example.bid_submission_rabbitmq.rabbitmq.RabbitMQProducer;
import com.example.bid_submission_rabbitmq.repository.BidSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidSubmissionService {


    private final BidSubmissionRepository repository;

    private final BidSubmissionMapper mapper;

    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public BidSubmissionService(BidSubmissionRepository repository, BidSubmissionMapper mapper, RabbitMQProducer rabbitMQProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.rabbitMQProducer = rabbitMQProducer;
    }


    public void updateTotalRate(BidSubmissionDTO dto) {

        Optional<BidSubmission> optionalBidSubmission = repository.findByTenderAndUser(dto.getTender(), dto.getUser());

        if (optionalBidSubmission.isPresent()) throw new RuntimeException("Bid Submission already calculated");

        repository.save(mapper.fromDTO(dto));

        rabbitMQProducer.send(new RabbitDTO(dto.getTotalRate(), dto.getUser().getId(), dto.getTender().getId(),dto.getSubmissionDateTime()));

    }
}
