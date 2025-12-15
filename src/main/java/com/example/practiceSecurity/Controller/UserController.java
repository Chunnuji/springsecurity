package com.example.practiceSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/admin1")
    public String adminFun1(){

        return "I am admin 1.";
    }

    @GetMapping("/admin2")
    public String adminFun2(){

        return "I am admin 2.";
    }

}
