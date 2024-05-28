package com.jobpulse.controller;

import com.jobpulse.dto.JobDto;
import com.jobpulse.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PostMapping("/add-job")
    public ResponseEntity<JobDto> saveJob(@RequestBody JobDto jobDto) {
        return ResponseEntity.ok().body(jobService.saveJob(jobDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable long id,@RequestBody JobDto jobDto){
        return ResponseEntity.ok().body(jobService.updateJob( id,jobDto));
    }

    @GetMapping("")
    public ResponseEntity<List<JobDto>> getAllJobs() {
        return ResponseEntity.ok().body(jobService.getAllJobs());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        return ResponseEntity.ok().body(jobService.deleteByID(id));
    }
}
