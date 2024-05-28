package com.jobpulse.dto;


import com.jobpulse.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;


}
