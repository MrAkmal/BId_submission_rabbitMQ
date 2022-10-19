package com.example.bid_submission_rabbitmq.service;

import com.example.bid_submission_rabbitmq.dto.LoginDTO;
import com.example.bid_submission_rabbitmq.dto.ResponseData;
import com.example.bid_submission_rabbitmq.entity.User;
import com.example.bid_submission_rabbitmq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean isAgencyUser(Long userId) {
        return repository.findById(userId).filter(user -> user.getRole().equals("ROLE_AGENCY_USER")).isPresent();
    }

    public User findById(Long userId) {

        Optional<User> optionalUser = repository.findById(userId);

        if (optionalUser.isEmpty()) throw new RuntimeException("User not found");

        return optionalUser.get();
    }


    public ResponseEntity<ResponseData<Void>> login(LoginDTO dto) {

        Optional<User> optionalUser = repository.findByName(dto.getName());

        if (optionalUser.isEmpty()) throw new RuntimeException("Wrong username");

        return new ResponseEntity<>(new ResponseData<>(null, "Successfully logged in"), HttpStatus.OK);
    }
}
