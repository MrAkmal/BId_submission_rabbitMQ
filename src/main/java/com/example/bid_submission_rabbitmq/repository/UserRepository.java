package com.example.bid_submission_rabbitmq.repository;


import com.example.bid_submission_rabbitmq.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
