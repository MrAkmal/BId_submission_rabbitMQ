package com.example.bid_submission_rabbitmq.controller;

import com.example.bid_submission_rabbitmq.dto.LoginDTO;
import com.example.bid_submission_rabbitmq.dto.ResponseData;
import com.example.bid_submission_rabbitmq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    private final UserService service;


    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseData<Void>> login(@RequestBody LoginDTO dto) {
        return service.login(dto);
    }


}
