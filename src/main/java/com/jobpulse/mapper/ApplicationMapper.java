package com.jobpulse.mapper;

import com.jobpulse.dto.ApplicationDto;
import com.jobpulse.dto.CategoryDto;
import com.jobpulse.dto.JobDto;
import com.jobpulse.entity.Application;
import com.jobpulse.entity.Category;
import com.jobpulse.entity.Job;
import com.jobpulse.entity.User;

public class ApplicationMapper {
    public ApplicationDto toDto(Application application){

            ApplicationDto applicationDto = new ApplicationDto();

            applicationDto.setId(application.getId());
            applicationDto.setEmail(application.getEmail());
            applicationDto.setFileName(application.getFileName());
            applicationDto.setPhoneNo(application.getPhoneNo());
            applicationDto.setName(application.getName());
            applicationDto.setAddress(application.getAddress());
            String imgUrl = "http://localhost:9191/api/v1/application/" + application.getId() + "/image";
            applicationDto.setImageUrl(imgUrl);
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setType(application.getJob().getCategory().getType());
            JobDto jobDto = new JobDto();
            jobDto.setId(application.getJob().getId());
            jobDto.setCountry(application.getJob().getCountry());
            jobDto.setJobPosted(application.getJob().getJobPosted());
            jobDto.setCategoryDto(categoryDto);
            applicationDto.setJobDto(jobDto);
            return applicationDto;

        }

        public Application toEntity (ApplicationDto applicationDto){

                Application application = new Application();

                application.setId(applicationDto.getId());
                application.setEmail(applicationDto.getEmail());
                application.setFileName(applicationDto.getFileName());
                application.setPhoneNo(applicationDto.getPhoneNo());
                application.setName(applicationDto.getName());
                application.setAddress(applicationDto.getAddress());
                User user = new User();
                user.setId(applicationDto.getUserDto().getId());
                application.setUser(user );
                Job job = new Job();
                job.setId(applicationDto.getJobDto().getId());
                application.setJob(job);
                return application;
        }
}
