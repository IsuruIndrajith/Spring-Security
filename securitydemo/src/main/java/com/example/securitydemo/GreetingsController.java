package com.example.securitydemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/hello")
    public String SayHello(){
        return "Hello";
    }
}
