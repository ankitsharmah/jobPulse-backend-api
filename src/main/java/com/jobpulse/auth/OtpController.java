//package com.secure.securelogin.auth;
//
//import com.secure.securelogin.service.EmailService;
//import com.secure.securelogin.service.OtpService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class OtpController {
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private OtpService otpService;
//
//    @PostMapping("/sendOtp/{email}")
//    public String sendOtp(@PathVariable String email) {
//        System.out.println("this is email "+email);
//
//    }
//}
