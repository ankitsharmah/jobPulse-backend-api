package com.jobpulse.service;

import com.jobpulse.dto.JobDto;
import com.jobpulse.entity.Category;
import com.jobpulse.entity.Job;
import com.jobpulse.mapper.JobMapper;
import com.jobpulse.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepo;

    //saving job
    public JobDto saveJob(JobDto jobDto){

        JobMapper jobMapper = new JobMapper();
        Job job = jobMapper.toEntity(jobDto);
        jobRepo.save(job);

       JobDto savedResponse = jobMapper.toDto(job);

        return savedResponse;
    }
//retrieving all jobs;
    public List<JobDto > getAllJobs(){
        List<Job> jobs = jobRepo.findAll();
        List<JobDto> jobDtos = new ArrayList<>();
        JobMapper jobMapper = new JobMapper();
        for (Job job :jobs
                ) {
            JobDto jobDto= jobMapper.toDto(job);
//            System.out.println("this is nul "+jobDto.getCategory().getJobs());
//            jobDto.getCategory().setJobs(null);
            jobDtos.add(jobDto);
        }

        return jobDtos;
    }
//updating a particular job
    public JobDto updateJob(long id, JobDto jobDto) {

        Job job = jobRepo.findById(id).get();


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

        job.setCategory(category);
        job.setCountry(jobDto.getCountry());
        job.setCity(jobDto.getCity());
        job.setSalary(jobDto.getSalary());
        job.setTitle(jobDto.getTitle());
        job.setLocation(jobDto.getLocation());
        job.setDescription(jobDto.getDescription());
        job.setId(job.getId());
        job.setApplications(job.getApplications());
        job.setJobPosted(job.getJobPosted());

        job=jobRepo.save(job);
//        JobMapper jobMapper1 = new JobMapper();

        return jobMapper.toDto(job);
    }

    //deleting a particular job
    public String deleteByID(long id) {
        jobRepo.deleteById(id);
        return "Deleted successfully";
    }
}
