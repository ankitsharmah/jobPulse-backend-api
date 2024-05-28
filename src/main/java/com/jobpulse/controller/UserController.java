package com.jobpulse.controller;

import com.jobpulse.dto.UserDto;
import com.jobpulse.entity.User;
import com.jobpulse.repository.ApplicationRepository;
import com.jobpulse.repository.UserRepository;
import com.jobpulse.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable long id){
        return ResponseEntity.ok().body(userService.find(id));
    }


}
