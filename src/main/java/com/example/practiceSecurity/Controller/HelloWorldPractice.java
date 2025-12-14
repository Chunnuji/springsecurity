package com.example.practiceSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldPractice {

    @GetMapping("/hello")
    public String myMethod(){
        return "Hello World";
    }

    @GetMapping("/hello2")
    public String myMethod2(){
        return "Hello World 2 !";
    }


}
