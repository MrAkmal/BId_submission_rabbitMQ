package com.example.bid_submission_rabbitmq.repository;


import com.example.bid_submission_rabbitmq.entity.BidSubmission;
import com.example.bid_submission_rabbitmq.entity.Tender;
import com.example.bid_submission_rabbitmq.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BidSubmissionRepository extends JpaRepository<BidSubmission, Long> {


    Optional<BidSubmission>  findByTenderAndUser(Tender tender, User user);

}
