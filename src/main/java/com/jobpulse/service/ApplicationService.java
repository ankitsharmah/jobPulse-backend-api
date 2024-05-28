package com.jobpulse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobpulse.dto.ApplicationDto;
import com.jobpulse.entity.Application;
import com.jobpulse.mapper.ApplicationMapper;
import com.jobpulse.repository.ApplicationRepository;
import com.jobpulse.utils.ImageUtils;
import jakarta.mail.Multipart;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;


    public String save(MultipartFile file, String applicationJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ApplicationDto application = objectMapper.readValue(applicationJson, ApplicationDto.class);
        System.out.println("converted in dto");
        ApplicationMapper applicationMapper = new ApplicationMapper();
        Application converted = applicationMapper.toEntity(application);
        converted.setImageData(ImageUtils.compressImage(file.getBytes()));
        converted.setFileName(file.getOriginalFilename());
        System.out.println("this is converted "+converted.getJob().getId());
        System.out.println("every thing is set its before save");
        applicationRepository.save(converted);
        System.out.println("its after");
        return "applied successfully";

    }

    public String updateApplication(ApplicationDto applicationDto){
        Application application = applicationRepository.findById(applicationDto.getId()).get();
            ApplicationMapper applicationMapper= new ApplicationMapper();
           Application newApplication = applicationMapper.toEntity(applicationDto);
           newApplication.setImageData(application.getImageData());
           newApplication.setFileName(application.getFileName());

           applicationRepository.save(newApplication);

           return "application Updated successfully";
    }
    public ResponseEntity<byte[]> getResumeImage(long id) {
        Application application = applicationRepository.findById(id).orElse(null);

        if (application == null || application.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }

        MediaType mediaType = switch (application.getFileName().substring(application.getFileName().lastIndexOf('.') + 1).toLowerCase()) {
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "pdf" -> MediaType.APPLICATION_PDF;

            // Add more cases for other image formats if needed
            default -> MediaType.APPLICATION_OCTET_STREAM; // Default to binary data if the type is unknown
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType); // adjust based on your image type

        return new ResponseEntity<>(ImageUtils.decompressImage(application.getImageData()), headers, HttpStatus.OK);
    }

    public String updateUserCv(long id,MultipartFile file) throws IOException {
        Application application = applicationRepository.findById(id).get();

        ApplicationMapper applicationMapper = new ApplicationMapper();

        application.setFileName(file.getOriginalFilename());
        application.setImageData(ImageUtils.compressImage(file.getBytes()));

        applicationRepository.save(application);
        return "cv updated successfully";
    }

    public String deleteApplication(long id) {
        applicationRepository.deleteById(id);
        return "Application deleted successfully ";
    }
}
