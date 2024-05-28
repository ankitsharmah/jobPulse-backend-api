package com.jobpulse.mapper;


import com.jobpulse.entity.Job;
import com.jobpulse.dto.CategoryDto;
import com.jobpulse.dto.JobDto;
import com.jobpulse.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    public CategoryDto toDto(Category category){
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setType(category.getType());
        List<JobDto> jobDtos = new ArrayList<>();
        for (Job job:category.getJobs()) {

            JobDto jobDto = new JobDto();

            //mapping
            jobDto.setJobPosted(job.getJobPosted());
            jobDto.setId(job.getId());
            jobDto.setCountry(job.getCountry());
            jobDto.setDescription(job.getDescription());
            jobDto.setSalary(job.getSalary());
            jobDto.setTitle(job.getTitle());
            jobDto.setLocation(job.getLocation());
            jobDto.setCity(job.getCity());

            jobDtos.add(jobDto);
        }
        categoryDto.setJobDtos(jobDtos);

        return categoryDto;
    }
}
