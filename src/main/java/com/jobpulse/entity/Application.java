package com.jobpulse.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "application_tb")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private String fileName;

    @Lob
    @Column(name = "fileData", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @ManyToOne()
    @JoinColumn(name = "job_id")
    private Job job;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

