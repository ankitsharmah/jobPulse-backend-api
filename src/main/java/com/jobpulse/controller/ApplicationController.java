package com.jobpulse.controller;

import com.jobpulse.dto.ApplicationDto;
import com.jobpulse.entity.Application;
import com.jobpulse.service.ApplicationService;
import com.jobpulse.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    @Transactional
    @PostMapping("/add")
    public ResponseEntity<String> addApplication(@RequestParam("file")MultipartFile file ,@RequestParam("application") String applicationJson) throws IOException {
        return ResponseEntity.ok().body(applicationService.save(file,applicationJson));
    }

    @PutMapping("/update-application")
    public ResponseEntity<String> updatedApplicationData(@RequestBody ApplicationDto applicationDto){
        return ResponseEntity.ok().body(applicationService.updateApplication(applicationDto));
    }
    @PutMapping("/update-application-cv/{id}")
    public ResponseEntity<String> updateCv(@PathVariable long id,MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(applicationService.updateUserCv(id,file));
    }
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getRoomImage(@PathVariable long id) {
        return applicationService.getResumeImage(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        return ResponseEntity.ok().body(applicationService.deleteApplication(id));
    }
}
