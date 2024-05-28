package com.jobpulse.service;


import com.jobpulse.entity.User;
import com.jobpulse.dto.AuthenticationRequest;
import com.jobpulse.dto.AuthenticationResponse;
import com.jobpulse.dto.RegisterRequest;
import com.jobpulse.repository.UserRepository;
import com.jobpulse.service.EmailService;
import com.jobpulse.service.JwtService;
import com.jobpulse.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final OtpService otpService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public String register(RegisterRequest request){
        // Generate OTP
        String otp = otpService.generateOtp(6); // Change the length as needed

        User user = userRepository.findByEmail(request.getEmail());
        if (user==null){
            //Saving user and setting otp in it
            var newUser = User.builder()
                    .email(request.getEmail())
                    .OtpGeneratedTime(LocalDateTime.now())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .name(request.getName())
                    .active(false)
                    .otp(otp)
                    .build();
            userRepository.save(newUser);
            // Send OTP via email
            emailService.sendOtpEmail(request.getEmail(), otp);
            return "Otp has been sent";
        }
        //updating otp
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        //if otp is expired
        emailService.sendOtpEmail(request.getEmail(), otp);
return "Otp has been sent";
    }


    public AuthenticationResponse verify(String otp){
        //fetching user by otp
        User user = userRepository.findByOtp(otp);
        // checking otp time and user
        if ( user != null && !user.isEnabled() && otp.equals(user.getOtp()) &&
                Duration.between(user.getOtpGeneratedTime(), LocalDateTime.now()).getSeconds()<(4*60)){
            System.out.println("this otp is valid "+user.toString());
            user.setActive(true);
            user.setOtp(null);
            user.setOtpGeneratedTime(null);
            userRepository.save(user);

            //generating jwtToken
            String jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        //if otp is not valid
        return AuthenticationResponse.builder()
                .token("Invalid Otp")
                .build();
    }


    public AuthenticationResponse authentication(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail());

        if (user!= null && user.isEnabled()){

            String jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        return AuthenticationResponse.builder()
                .token("please verify your email")
                .build();
    }
}
