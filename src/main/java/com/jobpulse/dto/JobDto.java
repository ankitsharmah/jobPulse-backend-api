package com.jobpulse.dto;

import com.jobpulse.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private long id;
    private String title;
    private String country;
    private String city;
    private String location;
    private String description;
    private LocalDateTime jobPosted;
    private String salary;
    private CategoryDto categoryDto;
    private List<ApplicationDto> applicationDto= new ArrayList<>();
}
