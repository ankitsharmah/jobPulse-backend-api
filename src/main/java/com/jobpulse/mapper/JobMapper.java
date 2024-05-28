package com.jobpulse.mapper;

import com.jobpulse.dto.CategoryDto;
import com.jobpulse.entity.Category;
import com.jobpulse.entity.Job;
import com.jobpulse.dto.ApplicationDto;
import com.jobpulse.dto.JobDto;
import com.jobpulse.entity.Application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobMapper {
    //converting job to job dto class
//    public JobDto toDto(Job job){
//        JobDto jobDto = new JobDto();
//
//    //mapping
//        Category category = new Category();
//        category.setType(job.getCategory().getType());
//        category.setId(job.getCategory().getId());
//        category.setJobs(null);
////        System.out.println("this sisssso "+job.getCategory().getType());
//        jobDto.setCategory(category);
//        jobDto.setJobPosted(LocalDateTime.now());
//        jobDto.setId(job.getId());
//        jobDto.setCountry(job.getCountry());
//        jobDto.setDescription(job.getDescription());
//        jobDto.setSalary(job.getSalary());
//        jobDto.setTitle(job.getTitle());
//        jobDto.setLocation(job.getLocation());
//        jobDto.setCity(job.getCity());
//
//        final List<ApplicationDto> applicationDtos = getApplicationDtos(job);
//        jobDto.setApplicationDto(applicationDtos);
//
//        return jobDto;
//    }

    public JobDto toDto(Job job) {
        JobDto jobDto = new JobDto();

        if (job.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto(); // Assuming you have a CategoryDto class
            categoryDto.setType(job.getCategory().getType());
            categoryDto.setId(job.getCategory().getId());
            jobDto.setCategoryDto(categoryDto);
        } else {
            // Handle the case where category is null
            // For example, you can set a default category or leave it null
            jobDto.setCategoryDto(null);
        }

        jobDto.setJobPosted(LocalDateTime.now());
        jobDto.setId(job.getId());
        jobDto.setCountry(job.getCountry());
        jobDto.setDescription(job.getDescription());
        jobDto.setSalary(job.getSalary());
        jobDto.setTitle(job.getTitle());
        jobDto.setLocation(job.getLocation());
        jobDto.setCity(job.getCity());

        final List<ApplicationDto> applicationDtos = getApplicationDtos(job);
        jobDto.setApplicationDto(applicationDtos);

        return jobDto;
    }

    private static List<ApplicationDto> getApplicationDtos(Job job) {
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        for (Application application:
             job.getApplications()) {

            ApplicationDto applicationDto = new ApplicationDto();
            applicationDto.setId(application.getId());
            applicationDto.setEmail(application.getEmail());
            applicationDto.setFileName(application.getFileName());
            applicationDto.setPhoneNo(application.getPhoneNo());
            applicationDto.setName(application.getName());
            applicationDto.setAddress(application.getAddress());
            applicationDto.setImageUrl("");

            applicationDtos.add(applicationDto);
        }
        return applicationDtos;
    }

    private static List<Application> getApplication(JobDto jobDto) {
        List<Application> applications = new ArrayList<>();
        for (ApplicationDto applicationDto:
                jobDto.getApplicationDto()) {

            Application application = new Application();
            application.setId(applicationDto.getId());
            application.setEmail(applicationDto.getEmail());
            application.setFileName(applicationDto.getFileName());
            application.setPhoneNo(applicationDto.getPhoneNo());
            application.setName(applicationDto.getName());
            application.setAddress(applicationDto.getAddress());
            application.setImageData(applicationDto.getImageData());

            applications.add(application);
        }
        return applications;
    }

    public Job toEntity(JobDto jobDto){
        Job job = new Job();

        Category category = new Category();

        category.setId(jobDto.getCategoryDto().getId());
        category.setType(jobDto.getCategoryDto().getType());
        List<Job> jobs = new ArrayList<>();
        JobMapper jobMapper = new JobMapper();
        for (JobDto jobDto1:jobDto.getCategoryDto().getJobDtos()
             ) {
            jobs.add(jobMapper.toEntity(jobDto1));
        }
        category.setJobs(jobs);
        //mapping
        job.setCategory(category);
        job.setJobPosted(LocalDateTime.now());
        job.setCountry(jobDto.getCountry());
        job.setDescription(jobDto.getDescription());
        job.setSalary(jobDto.getSalary());
        job.setTitle(jobDto.getTitle());
        job.setLocation(jobDto.getLocation());
        job.setCity(jobDto.getCity());


        return job;
    }
}
