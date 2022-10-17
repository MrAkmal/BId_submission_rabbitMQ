package com.example.bid_submission_rabbitmq.service;

import com.example.bid_submission_rabbitmq.dto.BidSubmissionDTO;
import com.example.bid_submission_rabbitmq.entity.BidSubmission;
import com.example.bid_submission_rabbitmq.mapper.BidSubmissionMapper;
import com.example.bid_submission_rabbitmq.repository.BidSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidSubmissionService {


    private final BidSubmissionRepository repository;

    private final BidSubmissionMapper mapper;

    @Autowired
    public BidSubmissionService(BidSubmissionRepository repository, BidSubmissionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public void updateTotalRate(BidSubmissionDTO dto){

        Optional<BidSubmission> optionalBidSubmission = repository.findByTenderAndUser(dto.getTender(), dto.getUser());

        if (optionalBidSubmission.isPresent()) throw new RuntimeException("Bid Submission already calculated");

        repository.save(mapper.fromDTO(dto));

    }
}
