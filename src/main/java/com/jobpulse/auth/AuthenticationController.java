package com.jobpulse.auth;


import com.jobpulse.dto.AuthenticationRequest;
import com.jobpulse.dto.AuthenticationResponse;
import com.jobpulse.dto.RegisterRequest;
import com.jobpulse.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    //Sending otp
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){

        //sending otp response
        return ResponseEntity.ok().body(authenticationService.register(request));

    }
    //Verifying otp
    @PutMapping("/verify-otp/{otp}")
    public ResponseEntity<AuthenticationResponse> verify(@PathVariable String otp){
        return ResponseEntity.ok().body(authenticationService.verify(otp));
    }

    //authenticating in login
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> auhtenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok().body(authenticationService.authentication(request));
    }
}
