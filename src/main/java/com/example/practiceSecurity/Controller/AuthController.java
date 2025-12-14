package com.example.practiceSecurity.Controller;

import com.example.practiceSecurity.DTO.LoginRequest;
import com.example.practiceSecurity.DTO.LoginResponse;
import com.example.practiceSecurity.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/signup")
    public String SignUp(@RequestBody LoginRequest loginRequest){
        loginService.signUp(loginRequest);
        return "Welcome "+ loginRequest.getUsername() +" ! You are signed up.";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(loginService.login(loginRequest));
    }
}
