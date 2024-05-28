package com.jobpulse.mapper;

import com.jobpulse.dto.ApplicationDto;
import com.jobpulse.dto.UserDto;
import com.jobpulse.entity.Application;
import com.jobpulse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public UserDto userDto(User user){

        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());

        List<ApplicationDto> applicationDtoList = new ArrayList<>();
        ApplicationMapper applicationMapper = new ApplicationMapper();
        for (Application application:user.getApplications()
             ) {
            applicationDtoList.add(applicationMapper.toDto(application));

        }

        userDto.setApplicationsDtos(applicationDtoList);
        return userDto;
    }
}
