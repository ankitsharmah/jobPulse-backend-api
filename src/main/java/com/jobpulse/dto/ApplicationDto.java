package com.jobpulse.dto;

import com.jobpulse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private long id;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private String fileName;
    private byte[] imageData;
    private UserDto userDto;
    private JobDto jobDto;
    private String imageUrl;
}
