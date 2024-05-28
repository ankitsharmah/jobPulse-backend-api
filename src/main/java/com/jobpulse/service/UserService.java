package com.jobpulse.service;

import com.jobpulse.dto.ApplicationDto;
import com.jobpulse.dto.UserDto;
import com.jobpulse.entity.Application;
import com.jobpulse.entity.User;
import com.jobpulse.mapper.ApplicationMapper;
import com.jobpulse.mapper.UserMapper;
import com.jobpulse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public UserDto find(long id) {
        User user = userRepository.findById(id).get();
        UserMapper userMapper = new UserMapper();
        return userMapper.userDto(user);
    }
}
