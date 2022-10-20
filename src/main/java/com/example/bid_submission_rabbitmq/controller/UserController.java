package com.example.bid_submission_rabbitmq.controller;

import com.example.bid_submission_rabbitmq.dto.LoginDTO;
import com.example.bid_submission_rabbitmq.dto.ResponseData;
import com.example.bid_submission_rabbitmq.entity.User;
import com.example.bid_submission_rabbitmq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin("*")
public class UserController {


    private final UserService service;


    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseData<User>> login(@RequestBody LoginDTO dto) {
        return service.login(dto);
    }


}
